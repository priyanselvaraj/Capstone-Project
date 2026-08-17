const API='http://localhost:8080/api';
const SESSION_KEY='sprs_session';

function currentUser(){try{return JSON.parse(localStorage.getItem(SESSION_KEY)||'null')}catch{return null}}
function setSession(user){localStorage.setItem(SESSION_KEY,JSON.stringify(user))}
function logout(){localStorage.removeItem(SESSION_KEY);location.href='index.html'}
function esc(s){return String(s??'').replace(/[&<>"']/g,m=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[m]))}
async function api(path,options={}) {
  const res=await fetch(API+path,{headers:{'Content-Type':'application/json',...(options.headers||{})},...options});
  let body={}; try{body=await res.json()}catch{}
  if(!res.ok || body.success===false) throw new Error(body.message||`Request failed (${res.status})`);
  return body.data;
}
function requireLogin(){
 const page=location.pathname.split('/').pop();
 if(['dashboard.html','suppliers.html','evaluation.html','reports.html'].includes(page)&&!currentUser()) location.href='index.html';
}
function showLoggedInUser(){const u=currentUser();document.querySelectorAll('.user').forEach(e=>e.textContent=u?.name||u?.username||'User')}
function score(e){return Math.round((+e.quality*.25)+(+e.delivery*.25)+(+e.cost*.20)+(+e.service*.15)+(+e.compliance*.15))}
function grade(s){return s>=90?'Excellent':s>=75?'Good':s>=60?'Average':'Poor'}

document.addEventListener('DOMContentLoaded',()=>{
 const path=location.pathname.split('/').pop()||'index.html';
 if(path==='index.html'){
  if(currentUser()){location.href='dashboard.html';return}
  document.getElementById('loginForm')?.addEventListener('submit',async e=>{
   e.preventDefault();
   try{
    const data=await api('/auth/login',{method:'POST',body:JSON.stringify({
      username:document.getElementById('username').value.trim(),
      password:document.getElementById('password').value
    })});
    setSession(data); location.href='dashboard.html';
   }catch(err){alert(err.message)}
  });
 }
 if(path==='register.html'){
  document.getElementById('registerForm')?.addEventListener('submit',async e=>{
   e.preventDefault();
   const p=document.getElementById('regPassword').value, c=document.getElementById('regConfirmPassword').value;
   if(p!==c){alert('Passwords do not match.');return}
   if(!/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[^A-Za-z0-9]).{8,}$/.test(p)){
    alert('Password must have at least 8 characters, one uppercase letter, one number and one symbol.');return
   }
   try{
    await api('/auth/register',{method:'POST',body:JSON.stringify({
      name:document.getElementById('regName').value.trim(),
      username:document.getElementById('regUsername').value.trim(),
      email:document.getElementById('regEmail').value.trim(),
      phone:document.getElementById('regPhone').value.trim(),
      password:p, role:'USER'
    })});
    alert('Registration successful. Please login.');location.href='index.html';
   }catch(err){alert(err.message)}
  });
 }
 requireLogin();showLoggedInUser();
 if(path==='dashboard.html') renderDashboard();
 if(path==='suppliers.html') renderSuppliers();
 if(path==='evaluation.html') initEvaluation();
 if(path==='reports.html') renderReports();
});

async function getSuppliers(){return await api('/suppliers')}
async function getRatings(){return await api('/ratings/ranking')}

async function renderDashboard(){
 try{
  const [s,rs]=await Promise.all([getSuppliers(),getRatings()]);
  const active=s.filter(x=>x.active).length;
  document.getElementById('totalSuppliers').textContent=s.length;
  document.getElementById('activeSuppliers').textContent=active;
  const avg=rs.length?Math.round(rs.reduce((a,x)=>a+x.overallScore,0)/rs.length):0;
  document.getElementById('avgScore').textContent=avg+'%';
  const best=rs[0]?.supplier?.name||'-';
  document.getElementById('topSupplier').textContent=best;
  document.getElementById('recentEvaluations').innerHTML=rs.slice(0,5).map(r=>`
   <tr><td>${esc(r.supplier?.name)}</td><td>${Math.round(r.qualityScore)}%</td>
   <td>${Math.round(r.deliveryScore)}%</td><td>${Math.round(r.overallScore)}%</td>
   <td><span class="badge ${grade(r.overallScore).toLowerCase()}">${esc(r.ratingCategory||grade(r.overallScore))}</span></td></tr>`).join('');
 }catch(e){alert(e.message)}
}

async function renderSuppliers(){
 try{
  const s=await getSuppliers(), q=(document.getElementById('supplierSearch')?.value||'').toLowerCase();
  document.getElementById('supplierTable').innerHTML=s.filter(x=>
   (x.name+' '+x.email+' '+(x.category||'')).toLowerCase().includes(q)
  ).map(x=>`<tr><td>${esc(x.supplierCode||('SUP-'+String(x.id).padStart(3,'0')))}</td>
   <td><b>${esc(x.name)}</b></td><td>${esc(x.email||'-')}</td><td>${esc(x.category||'-')}</td>
   <td>${x.active?'Active':'Inactive'}</td><td>${esc(x.phone||'-')}</td>
   <td><button class="btn" onclick="editSupplier(${x.id})">Edit</button>
   <button class="btn" onclick="deleteSupplier(${x.id})">Delete</button></td></tr>`).join('');
 }catch(e){alert(e.message)}
}
function openSupplierForm(id){
 document.getElementById('supplierModal').classList.remove('hidden');document.getElementById('supplierForm').reset();
 document.getElementById('supplierId').value=id||'';
 if(id){getSuppliers().then(list=>{const s=list.find(x=>x.id===id);document.getElementById('modalTitle').textContent='Edit Supplier';
  supplierName.value=s.name;supplierEmail.value=s.email;supplierCategory.value=s.category||'';supplierStatus.value=s.active?'Active':'Inactive';
 })}else document.getElementById('modalTitle').textContent='Add Supplier';
}
function editSupplier(id){openSupplierForm(id)}
function closeModal(){document.getElementById('supplierModal').classList.add('hidden')}
async function deleteSupplier(id){if(!confirm('Delete this supplier?'))return;try{await api('/suppliers/'+id,{method:'DELETE'});renderSuppliers()}catch(e){alert(e.message)}}
document.getElementById('supplierForm')?.addEventListener('submit',async e=>{
 e.preventDefault();const id=document.getElementById('supplierId').value;
 const payload={name:supplierName.value.trim(),email:supplierEmail.value.trim(),category:supplierCategory.value.trim(),active:supplierStatus.value==='Active'};
 try{
  if(id){await api('/suppliers/'+id,{method:'PUT',body:JSON.stringify(payload)})}
  else{payload.supplierCode='SUP-'+Date.now();await api('/suppliers',{method:'POST',body:JSON.stringify(payload)})}
  closeModal();renderSuppliers();
 }catch(err){alert(err.message)}
});

async function initEvaluation(){
 try{
  const s=await getSuppliers();document.getElementById('evalSupplier').innerHTML=s.filter(x=>x.active).map(x=>`<option value="${x.id}">${esc(x.name)}</option>`).join('');
  ['quality','delivery','cost','service','compliance'].forEach(id=>document.getElementById(id).addEventListener('input',updatePreview));updatePreview();
 }catch(e){alert(e.message)}
}
function currentEval(){return {quality:+quality.value,delivery:+delivery.value,cost:+cost.value,service:+service.value,compliance:+compliance.value}}
function updatePreview(){const e=currentEval(),s=score(e);scorePreview.textContent=s+'%';gradePreview.textContent=grade(s)}
document.getElementById('evaluationForm')?.addEventListener('submit',async e=>{
 e.preventDefault();const x=currentEval();
 try{
  await api('/ratings',{method:'POST',body:JSON.stringify({
   supplier:{id:+evalSupplier.value},qualityScore:x.quality,deliveryScore:x.delivery,costScore:x.cost,
   quantityAccuracyScore:100,communicationScore:x.service,complianceScore:x.compliance,
   ratingDate:new Date().toISOString().slice(0,10)
  })});
  alert('Evaluation submitted successfully!');location.reload();
 }catch(err){alert(err.message)}
});

async function renderReports(){
 try{
  const rs=await getRatings(), by={};
  rs.forEach(r=>{const id=r.supplier?.id;if(!by[id])by[id]={supplier:r.supplier,score:0,n:0};by[id].score+=r.overallScore;by[id].n++});
  const rows=Object.values(by).map(x=>({...x,score:Math.round(x.score/x.n)}));
  document.getElementById('reportBars').innerHTML=rows.map(x=>`<div class="bar-row"><b>${esc(x.supplier.name)}</b><div class="bar"><i style="width:${x.score}%"></i></div><strong>${x.score}%</strong></div>`).join('');
  document.getElementById('reportTable').innerHTML=rows.map(x=>{const g=grade(x.score);return `<tr><td>${esc(x.supplier.name)}</td><td>${x.score}%</td><td><span class="badge ${g.toLowerCase()}">${g}</span></td><td>${x.score>=90?'Continue partnership':x.score>=75?'Monitor performance':x.score>=60?'Improvement plan required':'Review supplier'}</td></tr>`}).join('');
 }catch(e){alert(e.message)}
}


const member_register_url = 'http://'+ip+':8080/api/sign';
const member_delete_url = 'http://'+ip+':8080/api/delete';
document.getElementById('register-btn').addEventListener('click', function(){
	const id = document.getElementById('id').value;
	const pw = document.getElementById('pw').value;	
	const name = document.getElementById('name').value;	
	const email = document.getElementById('email').value;
	const birthday = document.getElementById('birthday').value;
	const data = {dev_key, id, pw, name, email, birthday};
	console.log(data);
	fetch(member_register_url, {
		method:"POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify(data),
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		if(msg=='OK'){
			alert('통합 회원가입 성공');
			document.getElementById('register-modal-close-btn').click();
		}else{
			alert(detail);
		}
	});
});
document.getElementById('delete-btn').addEventListener('click', function(){
	const delete_check = document.getElementById('delete-check').checked;
	if(delete_check==false){
		alert('통합 회원탈퇴 희망을 체크해 주세요');
		return;
	}
	const id = document.getElementById('delete-id').value;
	const pw = document.getElementById('delete-pw').value;
	const data = {dev_key, id, pw};
	console.log(data);
	fetch(member_delete_url, {
		method:"POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify(data),
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const detail = obj['detail'];
		if(msg=='OK'){
			alert('통합 회원탈퇴 완료');
			document.getElemtentById('delete-modal-close-btn').click();
		}else{
			alert(detail);
		}
	});
});
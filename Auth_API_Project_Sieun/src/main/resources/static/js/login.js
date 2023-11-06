// 여기에 developer key를 작성하세요!!!
const dev_key = ''; // 여기에 developer key를 작성하세요!!!
// 여기에 developer key를 작성하세요!!!

const member_login_url = 'http://'+ip+':8080/api/login';

// 여기에 당신의 제품의 URL을 넣으세요!!!
const url = '';// 여기에 당신의 제품의 URL을 넣으세요!!!
//여기에 당신의 제품의 URL을 넣으세요!!!

document.getElementById('test-login-btn').addEventListener('click', function(){
	const id = document.getElementById('test-id').value;
	const pw = document.getElementById('test-pw').value;	
	const data = {dev_key, id, pw};
	fetch(member_login_url, {
		method:"POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify(data),
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		const msg = obj['msg'];
		const birthday = obj['brithday'];
		const name = obj['name'];
		const email = obj['email'];
		const detail = obj['detail'];
		if(msg=="OK"){
			// 로그인 성공
			document.getElementById('login-test-modal-close-btn').click();
			const user_info = {id, pw, birthday, name, email}; // 당신의 서버로 보낼 사용자 데이터를 골라서 담으세요
			fetch(url, {
				method:"POST",
				headers: {"Content-Type": "application/json"},
				body: JSON.stringify(user_info),
			})
		}else{
			// 로그인 실패
			alert(detail);
		}
	});
});
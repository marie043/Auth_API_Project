/**
 * 
 */
// 키 발급 URL
const key_generate_url = "http://"+ip+":8080/dev/key"


// 개발자 키 발급 부분 안보이게..
document.getElementById("key-div").style.display = "none";

// 개발자 키 모달의 버튼이 눌리면...
document.getElementById("generate-key-btn").addEventListener("click", function(){
	const id = document.getElementById("dev-id").value;
	const pw = document.getElementById("dev-pw").value;
	const data = {id, pw};
	console.log(data);
	fetch(key_generate_url, {
		method:"POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify(data),
	}).then(function(res){
		return res.json();
	}).then(function(obj){
		console.log(obj)
		let msg = obj["msg"];
		let key = obj["key"];
		if(key!=null){
			document.getElementById("key-div").style.display = "block";
			document.getElementById("dev-key-p").innerText = key;
			document.getElementById("key-modal-close-btn").click();
			dev_key = key;
			alert(msg);
			return;
		}else{
			alert(msg);
			return;
		}
	});
});
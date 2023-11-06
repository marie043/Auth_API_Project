let copyText = null;
let range = document.createRange();
var selection = window.getSelection();

document.getElementById('copy-register-code-copy-btn').addEventListener('click', function(){
	copyText = document.getElementById('register-code');
	copy();
	copyText = null;
});
document.getElementById('copy-login-code-copy-btn').addEventListener('click', function(){
	copyText = document.getElementById('login-code');
	copy();
	copyText = null;
});
document.getElementById('copy-delete-code-btn').addEventListener('click', function(){
	copyText = document.getElementById('delete-code');
	copy();
	copyText = null;
});
document.getElementById('copy-config1').addEventListener('click', function(){
	copyText = document.getElementById('config-code1');
	copy();
	copyText = null;
});
document.getElementById('copy-config2').addEventListener('click', function(){
	copyText = document.getElementById('config-code2');
	copy();
	copyText = null;
});
document.getElementById('copy-config3').addEventListener('click', function(){
	copyText = document.getElementById('config-code3');
	copy();  
	copyText = null;
});
document.getElementById('copy-ip').addEventListener('click', function(){
	copyText = document.createElement('textarea');
	copyText.innerText = ip;
	document.body.appendChild(copyText);
	copy();  
	document.body.removeChild(copyText);
	copyText = null;
});
function copy(){
	range.selectNodeContents(copyText);
	selection.addRange(range);
	copyText.setSelectionRange(0, 999999);
	document.execCommand("copy");
	copyText.setSelectionRange(0, 0);
	selection.removeAllRanges();
}
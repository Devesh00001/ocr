
var count = 1;
function addField() {
  count++;
  if(count<5){
  html =  ' <label for="regex'+count+'" style="color: white">'+count+' Regex:</label><br> <input type="text" id="regex'+count+'" name="regex'+count+'" required><br>'
  var form = document.getElementById('regex')
  form.innerHTML += html

  }
}


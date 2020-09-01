 function showSSHKEY(v){
    if(v=='0'){
		$('#div1').show();
    }
    if(v=='1'){
		$('#div1').hide();
     }
  }
 function showSSHKEY1(v){
	 if(v=='0'){
		 $('#div4').show();
	 }
	 if(v=='1'){
		 $('#div4').hide();
	 }
 }
  function showAGREE(v,portid){
	  if(v=='1'){
		  $('#'+portid).val('22');
	    }
	  if(v=='2'){
		  $('#'+portid).val('3389');
	  }
	  if(v=='3'){
		  $('#'+portid).val('23');
	  }
	  if(v=='4'){
		  $('#'+portid).val('5901');
	  }
  }
  function sel(r){
	    o=document.getElementsByName(r)
	    for(i=0;i<o.length;i++)
	      o[i].checked=event.srcElement.checked
	}

function sel(r){
    o=document.getElementsByName(r)
    for(i=0;i<o.length;i++)
      o[i].checked=event.srcElement.checked
}
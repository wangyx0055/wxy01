function sel(r){
    var o=document.getElementsByName(r);
    for(i=0;i<o.length;i++)
        o[i].checked=event.srcElement.checked
}

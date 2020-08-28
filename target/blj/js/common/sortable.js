$("#sortable").sortable({   
        items: "tr",                       //只是tr可以拖动  
        opacity:0.6,                  //拖动时，透明度为0.6  
		axis: "y",
		stop: function(event, ui ) {
			var data = $(this).sortable('toArray');
			console.log(data)
		}
	});

$("#sortable").disableSelection();

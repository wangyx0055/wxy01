$("#sortable").sortable({   
        items: "tr",                       //只是tr可以拖动  
        opacity:0.6,                  //拖动时，透明度为0.6  
		stop: function() {
			//记录sort后的id顺序数组
			let arr = $("#sortable").sortable('toArray');
			console.log(arr);
			//拖拽后利用localStorage记录顺序
			localStorage.arr = arr;
		}
	});
	let localSt = localStorage.arr;
	//如果有localst记录则，按照这个进行排序元素
	if (localSt) {
		let resArr = localSt.split(',');
		let resUl = $('ul');
		//li 数组
		for (let i = 0; i < resArr.length; i++) {
			resUl.append($("#" + resArr[i]));
		}
	}
$("#sortable").disableSelection();

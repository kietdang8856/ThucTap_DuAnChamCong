(function(){
	// $(document).on('click', '#btn-add', function(event) {
	// 	var form = $(this).data('form');
	// 	var action = $(this).data('action');
	// 	var refresh = $(this).data('refresh');
	// 	var data_r = $('form#'+form).serialize();
	// 	$.ajax({
	// 		url: action,
	// 		type: 'POST',
	// 		data: data_r,
	// 		success:function(res){
	// 			var datas = $.parseJSON(res);
	// 			alert_txt(datas);
	// 			$('#'+refresh).load(location.href + " #"+refresh+">*","");
	// 		},
	// 		error:function(res){
	// 			var datas = $.parseJSON(res);
	// 			alert_txt(datas);
	// 		}
	// 	});
	// });

	// $(document).on('click','#btn-remove',function(){
	// 	var form = $(this).data('form');
	// 	var action = $(this).data('action');
	// 	var refresh = $(this).data('refresh');
	// 	var data_r = $('form#'+form).serialize();
	// 	$.ajax({
	// 		url: action,
	// 		type: 'POST',
	// 		data: data_r,
	// 		success:function(res){
	// 			var datas = $.parseJSON(res);
	// 			alert_txt(datas);
	// 			$('#'+refresh).load(location.href + " #"+refresh+">*","");
	// 		},
	// 		error:function(res){
	// 			var datas = $.parseJSON(res);
	// 			alert_txt(datas);
	// 		}
	// 	});
	// });

	// $(document).on('click','#btn-delete',function(ev){
	// 	ev.preventDefault();
	// 	var token = $('meta[name="_token"]').attr('content');
	// 	var action = $(this).attr('href');
	// 	var refresh = $(this).data('refresh');
	// 	// alert(token);
	// 	$.ajax({
	// 		url: action,
	// 		type: 'POST',
	// 		data:{"_token":token},
	// 		success:function(res){
	// 			var datas = $.parseJSON(res);
	// 			alert_txt(datas);
	// 			$('#'+refresh).load(location.href + " #"+refresh+">*","");
	// 		},
	// 		error:function(res){
	// 			var datas = $.parseJSON(res);
	// 			alert_txt(datas);
	// 		}
	// 	});
	// });

	// function alert_txt(data){
	// 	$.toast({
	// 	    heading: data.heading,
	// 	    text:data.text,
	// 	    icon: data.icon,
	// 	    position:"bottom-right"
	// 	});
	// }

	$(document).on('click', 'input#check-all', function(event) {
		var check = $(this).is(':checked');
		if (check) {
			$('input.item-check').prop({
				checked: true
			});
		}else{
			$('input.item-check').prop({
				checked: false
			});
		}
	});

}(jQuery));
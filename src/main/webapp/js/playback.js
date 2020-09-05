/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var recording;
var recordingDisplay;
var page_start = 0;
var page_length = 10;
function Replay(url){

	(function initExamplePlayer() {

	    /**
	     * The URL of the Guacamole session recording which should be played back.
	     *
	     * @constant
	     * @type String
	     */
	    var RECORDING_URL = url;

	    /**
	     * The element representing the session recording player.
	     *
	     * @type Element
	     */
	    var player = document.getElementById('player');

	    /**
	     * The element which will contain the recording display.
	     *
	     * @type Element
	     */
	    var display = document.getElementById('display');

	    /**
	     * Play/pause toggle button.
	     *
	     * @type Element
	     */
	    var playPause = document.getElementById('play-pause');

	    /**
	     * Button for cancelling in-progress seek operations.
	     *
	     * @type Element
	     */
	    var cancelSeek = document.getElementById('cancel-seek');

	    /**
	     * Text status display indicating the current playback position within the
	     * recording.
	     *
	     * @type Element
	     */
	    var position = document.getElementById('position');

	    /**
	     * Slider indicating the current playback position within the recording,
	     * and allowing the user to change the playback position.
	     *
	     * @type Element
	     */
	    var positionSlider = document.getElementById('position-slider');

	    /**
	     * Text status display indicating the current length of the recording.
	     *
	     * @type Element
	     */
	    var duration = document.getElementById('duration');

	    /**
	     * The tunnel which should be used to download the Guacamole session
	     * recording.
	     *
	     * @type Guacamole.Tunnel
	     */
	    var tunnel = new Guacamole.StaticHTTPTunnel(RECORDING_URL);

	    /**
	     * Guacamole.SessionRecording instance to be used to playback the session
	     * recording.
	     *
	     * @type Guacamole.SessionRecording
	     */
	    recording = new Guacamole.SessionRecording(tunnel);

	    /**
	     * The Guacamole.Display which displays the recording during playback.
	     *
	     * @type Guacamole.Display
	     */
	    var recordingDisplay = recording.getDisplay();

	    /**
	     * Converts the given number to a string, adding leading zeroes as necessary
	     * to reach a specific minimum length.
	     *
	     * @param {Numer} num
	     *     The number to convert to a string.
	     *
	     * @param {Number} minLength
	     *     The minimum length of the resulting string, in characters.
	     *
	     * @returns {String}
	     *     A string representation of the given number, with leading zeroes
	     *     added as necessary to reach the specified minimum length.
	     */
	    var zeroPad = function zeroPad(num, minLength) {

	        // Convert provided number to string
	        var str = num.toString();

	        // Add leading zeroes until string is long enough
	        while (str.length < minLength)
	            str = '0' + str;

	        return str;

	    };

	    /**
	     * Converts the given millisecond timestamp into a human-readable string in
	     * MM:SS format.
	     *
	     * @param {Number} millis
	     *     An arbitrary timestamp, in milliseconds.
	     *
	     * @returns {String}
	     *     A human-readable string representation of the given timestamp, in
	     *     MM:SS format.
	     */
	    var formatTime = function formatTime(millis) {

	        // Calculate total number of whole seconds
	        var totalSeconds = Math.floor(millis / 1000);

	        // Split into seconds and minutes
	        var seconds = totalSeconds % 60;
	        var minutes = Math.floor(totalSeconds / 60);

	        // Format seconds and minutes as MM:SS
	        return zeroPad(minutes, 2) + ':' + zeroPad(seconds, 2);

	    };

	    // Add playback display to DOM
	    display.appendChild(recordingDisplay.getElement());

	    // Begin downloading the recording
	    recording.connect();

	    // If playing, the play/pause button should read "Pause"
	    recording.onplay = function() {
	        //playPause.textContent = '暂停';
			$(playPause).removeClass('fa-play').addClass('fa-pause');
	    };

	    // If paused, the play/pause button should read "Play"
	    recording.onpause = function() {
	        //playPause.textContent = '播放';
			$(playPause).removeClass('fa-pause').addClass('fa-play');
	    };

	    // Toggle play/pause when display or button are clicked
	    display.onclick = playPause.onclick = function() {
	        if (!recording.isPlaying())
	            recording.play();
	        else
	            recording.pause();
	    };

	    // Resume playback when cancel button is clicked
	    cancelSeek.onclick = function cancelSeekOperation(e) {
	        recording.play();
	        player.className = '';
	        e.stopPropagation();
	    };

	    // Fit display within containing div
	    recordingDisplay.onresize = function displayResized(width, height) {

	        // Do not scale if display has no width
	        if (!width)
	            return;

	        // Scale display to fit width of container
	        recordingDisplay.scale(display.offsetWidth / width);

	    };

	    // Update slider and status when playback position changes
	    recording.onseek = function positionChanged(millis) {
	        position.textContent = formatTime(millis);
	        positionSlider.value = millis;
	    };

	    // Update slider and status when duration changes
	    recording.onprogress = function durationChanged(millis) {
	        duration.textContent = formatTime(millis);
	        positionSlider.max = millis;
	    };

	    // Seek within recording if slider is moved
	    positionSlider.onchange = function sliderPositionChanged() {

	        // Request seek
	        recording.seek(positionSlider.value, function seekComplete() {

	            // Seek has completed
	            player.className = '';

	        });

	        // Seek is in progress
	        player.className = 'seeking';
	    };
		recordingDisplay.onresize(document.getElementById('player').offsetWidth)
	})();

}

function setVideoPos(value){
	recording.seek(value);
}

function getKeys(url){
	$.ajax({
	    url:url,
	    type:"POST",
	    data:{
	    },
	    success:function(data){
	    	var backspace = '<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 512 512"><title>ionicons-v5-a</title><polyline points="244 400 100 256 244 112" style="fill:none;stroke:#000;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px"/><line x1="120" y1="256" x2="412" y2="256" style="fill:none;stroke:#000;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px"/></svg>'
	    	for(key in data){
	    		$('.list-group .commands').append('<li><div class="each_command" data-toggle="tooltip" title="'+data[key]+'" onclick="change('+data.val+');">'+data[key].replace('<Backspace>', backspace)+'</li>')
	    	}
	    	$('.list-group .commands div').tooltip();
	    },
	    error:function(){

	    }
	})
}


var _commands = function(isapppub, record_id, field, value) {
	$.ajax({
	    url: "../recordCommand/listRecordCommand?is_apppub="+isapppub+"&record_id="+record_id,
	    type: "POST",
	    data:{
			start: page_start,
			length: page_length
	    },
	    success:function(data){
			$('#commands').html('');
			var data = data['data'];
	    	for(key in data){
	    		$('#commands').append('<dd><span><div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:70px;" onclick="setVideoPos('+data[key].relatime+')" data-html="true" data-toggle="tooltip" title="时间:'+data[key]._time+'">'+data[key]._time.substring(11)+'</div></span><span>'+data[key].command+'</span></dd>')
	    	}
	    	//$('.list-group .commands div').tooltip();
			page_start = page_start+page_length;
	    },
	    error:function(){

	    }
	})
	/*
    $('#commands')
        .DataTable(
            {
                'paging' : true,
                'lengthChange' : true,
                'dom' : 't<"bottom"p<"clear">>',
                'searching' : false,
                'ordering' : true,
                "pagingType": "simple",
                'info' : true,
                'autoWidth' : false,
                "serverSide" : true,
                "destroy" : true,
                "ajax" : {
                    "url" : "../recordCommand/listRecordCommand?is_apppub="+isapppub+"&record_id="+record_id,
                    "data" : function(d) {
                        for(var key in d){
                            if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                                delete d[key];
                            }
                        }
                    	if(field!=null&&field!='')
                    		eval('d.' + field + '="' + value + '"');

                    }
                },
                "columns" : [
                    {
                        "data" : "_time",
                        "render" : function(data, type,
                                row, mata) {
				                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:70px;" onclick="setVideoPos('+row.relatime+')" data-html="true" data-toggle="tooltip" title="时间:'+data+'">'
				                    + data.substring(11)
				                    + '</div>';
				
				            }
                    },
                    {
                        "data" : "command",
                        "render" : function(data, type,
                                row, mata) {
                        		
				                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:90px;'+(window.parent.$('#command').val()!=""&&data.indexOf(window.parent.$('#command').val())>=0?'color:red':'')+'" data-html="true" data-toggle="tooltip" title="命令:'+data+'">'
				                    + data
				                    + '</div>';
				
				            }
                    }],
                "initComplete": function( settings, json ) {
                    $('#commands div').tooltip();
                }
            });
	*/
}

$("#sousuoId").click(function() {
	_commands($('#Distinguish').val(), $('#searchId').val());
});

$(document).ready(function() {
	// 选项卡 鼠标点击
	$('#display').css('height', (document.documentElement.clientHeight-50)+"px").css('width', (document.documentElement.clientWidth-300)+"px");
	$('#player').css('height', (document.documentElement.clientHeight-50)+"px").css('width', (document.documentElement.clientWidth-300)+"px");
	$('.m-left').css('height', (document.documentElement.clientHeight-10)+"px")
	$(".TAB_CLICK li").click(function() {
		var tab = $(this).parent(".TAB_CLICK");
		var con = tab.attr("id");
		var on = tab.find("li").index(this);
		$(this).addClass('on').siblings(tab.find("li")).removeClass('on');
		$(con).eq(on).show().siblings(con).hide();
	});
	$('.TAB_CLICK').each(function(index, el) {
		if ($(this).find('li.on').length) {
			$(this).find("li.on").trigger('click');
		} else {
			$(this).find("li").filter(':first').trigger('click');
		}
	});
});
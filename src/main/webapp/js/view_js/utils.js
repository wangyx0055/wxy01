function get_protocol_name(protocol_id)
{
	if (protocol_id === 1) {
       return "ssh";
    } else if (protocol_id === 2) {
    	return "rdp";
    } else if (protocol_id === 3) {
    	return "telnet";
    } else if (protocol_id === 4) {
    	return "vnc";
    } else if (protocol_id === 5) {
    	return "ftp";
    } else if (protocol_id === 6) {
    	return "sftp";
    } else {
    	return "unknown";
    }
}
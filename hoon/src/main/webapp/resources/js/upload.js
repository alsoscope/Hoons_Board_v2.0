function checkImageType(fileName) {
	var pattern = /jpg$|gif$|png$|jpeg$/i;
	
	return fileName.match(pattern);
};

function getFileInfo(fullName) {
	var fileName, imgSrc, getLink;
	var fileLink;
	
	if (checkImageType(fullName)) {
		imgSrc = "/boards/displayFile?fileName=" + fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0, 12);
		var end = fullName.substr(14);
		
		getLink = "/boards/displayFile?fileName=" + front + end;
	} else {
		imgSrc = "/resources/dist/img/file.png";
		fileLink = fullName.substr(12);
		getLink = "/boards/displayFile?fileName=" + fullName;
	}
	
	fileName = fileLink.substr(fileLink.indexOf("_") + 1);
	
	return {fileName:fileName, imgSrc:imgSrc, getLink:getLink, fullName:fullName};
}
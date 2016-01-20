Human = function(name,sex,age){
	this.name='human';
	doThings=function(event){
		return this.name +" is "+event+"ing";
	}
	
};
Person = function(){
	this.address = address;
	this.addEvents('walk','eat');
};

Ext.extend(Person,Human,{
	
});
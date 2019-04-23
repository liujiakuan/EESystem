$(document).ready(function(){
	$("#selectDepartment").change(function(){
		if($.trim($(this).val()) != $.trim("请点击选择部门")){
			window.location.href = '/EESystem/admEmployeeByDepartment.action?es_employee_department='+$(this).val();
		}
	});
});
$(document).ready(function(){

    Materialize.updateTextFields();
    
    $(".button-collapse").sideNav();
    $(".collapse").collapsible();
    $("select").material_select();
    $(".modal-trigger").leanModal();
    
    
    $(document).on("click", ".edit", function(){
        var id = $(this).data("empid");
        window.location.href = "http://localhost:8080/employee/edit?id="+id;
    });
    
    $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 100,
        format: 'mmmm dd, yyyy'
    });
    
    $(".datepicker").on("load", function(){
      var $input = $(this).pickadate();
      var picker = $input.pickadate('picker');
      var value = $(this).val();
      
      picker.set("select", value, {format: "mmmm dd, yyyy"}); 
    });
    
    $(document).on("click", ".add_contact", function(){
        var type = $(this).attr("id");
        var content = "<div class='row'><div class='input-field col s7'><input class='contact_details' name='contact_details'><input type='hidden' name='contact_type' value='"+type+"'><input type='hidden' name='contact_id' value='new'><label class='active'>"+type+"</label></div><div class='col s2'><i class='material-icons small btn waves-effect waves-light btn-icon red lighten-1 contact_delete'>close</i></div></div>";
        
        $("#contact_list").append(content);
    });
    
    $(document).on("click", ".contact_delete", function(){
        var type = $(this).closest(".row").find("input[name=contact_type]").val();
        Materialize.toast(type+' contact has been deleted', 4000);
        $(this).closest(".row").remove();        
    });
    
    $("input[name=gwa]").focusout(function(){
        var input = parseFloat($(this).val());
        if(input < 1.0 || input > 5.0 || input === NaN){
            $(this).removeClass("valid").addClass("invalid");
        }
    });
    
    $(document).on("click", ".role_edit", function(){
        var id = parseInt($(this).data("roleid"));
        var name = $(this).data("rolename");
        $("#roleModal").find("input[name=newRole]").val(name);
        $("#roleModal").find("input[name=newRoleId]").val(id);
        
        Materialize.updateTextFields();
        $("#roleModal").openModal();
    });
    
});

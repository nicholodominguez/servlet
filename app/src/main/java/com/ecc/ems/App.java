package com.ecc.ems;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.Set;

import com.ecc.ems.EmployeeService;
import com.ecc.ems.Employee;
import com.ecc.ems.Name;
import com.ecc.ems.Address;
import com.ecc.ems.Contact;
import com.ecc.ems.Role;
import com.ecc.ems.InputValidator;

public class App{
    public static void main(String args[]) {
         
        EmployeeService em = new EmployeeService();
        int choice;
        
        do{
            choice = App.printMenu();
            switch(choice) {
                case 1:
                    Employee emp = new Employee();
                    
                    App.editEmployee(em, emp);
                    break;
                case 2:
                    App.listMenu(em);
                    break;
                case 3:
                    emp = App.searchEmployee(em, "edit");
                    
                    App.editEmployee(em, emp);
                    break;
                case 4:
                    emp = App.searchEmployee(em, "delete");
                    
                    App.deleteEmployee(em, emp);
                    break;
                case 5:                    
                    App.addNewRoleType(em);
                    break;
                case 6:
                    App.editRoleType(em);
                    break;
                case 7:
                    App.deleteRoleType(em);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (choice != 8);
    }
    
    public static int printMenu() {
        System.out.println();
        System.out.println("==============================");
        System.out.println("|----------------------------|");
        System.out.println("| Employee Management System |");
        System.out.println("|----------------------------|");
        System.out.println("| [1] Add Employee           |");
        System.out.println("| [2] List Employee          |");
        System.out.println("| [3] Edit Employee          |");
        System.out.println("| [4] Delete Employee        |");
        System.out.println("|                            |");
        System.out.println("|----------------------------|");
        System.out.println("| Role Management System     |");
        System.out.println("|----------------------------|");
        System.out.println("| [5] Add Role               |");
        System.out.println("| [6] Edit Role              |");
        System.out.println("| [7] Delete Role            |");
        System.out.println("|                            |");
        System.out.println("|----------------------------|");
        System.out.println("| [8] Exit                   |");
        System.out.println("==============================");
        return InputValidator.getInputMenu(" Option: ", 8);
    }
    
    public static int printAddEmployeeMenu(Employee emp) {
        System.out.println();
        System.out.println("============================");
        System.out.println(" Employee Creation          ");
        System.out.println("============================");
        printEmployeeInfo(emp, true);
        System.out.println("[8] Save");
        System.out.println("[9] Cancel");
        System.out.println("----------------------------");
        return InputValidator.getInputMenu(" Option: ", 9);
    }
    
    public static void printEmployeeInfo(Employee emp, boolean isMenu) {
        List<String> empInfo = emp.stringify();
        Set<Contact> empContactInfo = emp.getContacts();
        Set<Role> empRoleInfo = emp.getRoles();
        int i = 1;
        
        System.out.println();
        for(; i <= empInfo.size(); i++){
            if(isMenu){
                System.out.print("[" + i + "] "); 
            }
            System.out.println(empInfo.get(i-1));    
        }
        
        if(isMenu){
            System.out.print("[" + i++ + "] "); 
        }
        System.out.println("Contacts:");
        
        if(empContactInfo != null){
            for(Contact contact : empContactInfo){
                System.out.println("\t" + contact.getContactType() + ": " + contact.getContactDetails());  
            }
        }
        
        if(isMenu){
            System.out.print("[" + i++ + "] "); 
        }
        System.out.println("Roles :");
        if(empRoleInfo != null){
            for(Role role : empRoleInfo){
                System.out.println("\t" + role.getName());  
            }
        }
    }
    
    public static void listMenu(EmployeeService es){
        List<Employee> empList = null;
        int choice = 1;
        boolean ascending = true;
        
        choice = printListMenu();
        
        if(choice == 4){
            return;
        }
        
        ascending = printOrderMenu() == 1 ? true : false;
        switch(choice){
            case 1:
                empList = es.listEmployeeByDateHired(ascending);
                break;
            case 2:
                empList = es.listEmployeeByLastname(ascending);
                break;
            case 3:
                empList = es.listEmployeeByGwa(ascending);
                break;
            case 4:
                break;
        }
        
        
        App.printEmployees(empList, false);
    }
    
    public static int printListMenu(){
        System.out.println();
        System.out.println("============================");
        System.out.println(" Sort list by:  ");
        System.out.println("============================");
        System.out.println(" [1] Date Hired");
        System.out.println(" [2] Last name");
        System.out.println(" [3] GWA");
        System.out.println(" [4] Cancel");
        System.out.println("============================");
        return InputValidator.getInputMenu(" Option: ", 4);
    }
    
    public static int printOrderMenu(){
        System.out.println();
        System.out.println("============================");
        System.out.println(" Order:  ");
        System.out.println("============================");
        System.out.println(" [1] Ascending");
        System.out.println(" [2] Descending");
        System.out.println("============================");
        return InputValidator.getInputMenu(" Option: ", 2);
    }
    
    public static void editEmployee(EmployeeService em, Employee emp) {
        Name nameInput;
        Address addressInput;
        Date dateInput;
        String strInput;
        Set<Contact> contactsInput;
        Set<Role> rolesInput;
        Double gwaInput;
        int choice = 1;
        Integer empId = emp.getId();
        
        do{
            choice = App.printAddEmployeeMenu(emp);
            switch(choice) {
                case 1:
                    nameInput = emp.getName() == null ? new Name() : emp.getName();
                    nameInput = App.nameMenu(nameInput);
                    if(nameInput != null){
                        emp.setName(nameInput);
                    }
                    break;
                case 2:
                    addressInput = emp.getAddress() == null ? new Address() : emp.getAddress();
                    addressInput = App.addressMenu(addressInput);
                    if(addressInput != null){
                        emp.setAddress(addressInput);
                    }
                    break;
                case 3:
                    dateInput = InputValidator.getInputDate("Enter birthdate (yyyy-mm-dd): ");
                    if(dateInput != null){
                        emp.setBdate(dateInput);
                    }
                    break;
                case 4:
                    gwaInput = InputValidator.getInputDouble("Enter GWA: ");
                    emp.setGwa(gwaInput);
                    break;
                case 5:
                    dateInput = InputValidator.getInputDate("Enter date hired (yyyy-mm-dd): ");
                    if(dateInput != null){
                        emp.setDateHired(dateInput);
                    }
                    break;
                case 6:
                    if(empId == null){
                        System.out.println("Can't add contacts. Save the employee info first.");
                        continue;
                    }
                    contactsInput = emp.getContacts() == null ? new HashSet<Contact>() : emp.getContacts();
                    contactsInput = App.contactsMenu(contactsInput, empId);
                    if(contactsInput != null){
                        emp.setContacts(contactsInput);
                        em.updateEmployee(emp);
                        System.out.println("After save: "+emp.getContacts().size());
                    }
                    break;
                case 7:
                    if(empId == null){
                        System.out.println("Can't add roles. Save the employee info first.");
                        continue;
                    }
                    rolesInput = emp.getRoles() == null ? new HashSet<Role>() : emp.getRoles();
                    rolesInput = App.rolesMenu(em, rolesInput, empId);
                    if(rolesInput != null){
                        emp.setRoles(rolesInput);
                        em.updateEmployee(emp);
                    }
                    break;
                case 8:
                    em.addEmployee(emp);
                    return;
                case 9:
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (choice != 8  && choice != 9);
           
    }
    
    public static void printEmployees(List<Employee> empList, boolean isMenu) {
        int i = 1;
        
        System.out.println();
        
        if(empList != null){
            for(Employee emp : empList){
                System.out.println("============================");
                if(isMenu){
                    System.out.print("[" + i + "] ");
                }
                System.out.println("Employee " + i++);
                System.out.println("============================");
                App.printEmployeeInfo(emp, false);
                System.out.println();
            }
        }
        else{
            System.out.println("No employee found.");
        }   
    }
    
    /* int choice: 1 - title, 2 - firstname, 3 - middlename, 4 - lastname,  5 - suffix */
    public static Name nameMenu(Name name){
        int choice = 1;   
        int i; 
        List<String> nameInfo;
        String input;
        
        do{    
            choice = printNameMenu(name);
            switch(choice) {
                case 1:
                    input = InputValidator.getInputName(choice);
                    name.setTitle(input);
                    break;
                case 2:
                    input = InputValidator.getInputName(choice);
                    name.setFirstname(input);
                    break;
                case 3:
                    input = InputValidator.getInputName(choice);
                    name.setMiddlename(input);
                    break;
                case 4:
                    input = InputValidator.getInputName(choice);
                    name.setLastname(input);
                    break;
                case 5:
                    input = InputValidator.getInputName(choice);
                    name.setSuffix(input);
                    break;
                case 6:
                    return name;
                case 7:
                    return null;
            }
        } while (choice != 6 && choice != 7);
        
        return null;
    }
    
    public static int printNameMenu(Name name){
        int i = 1;
         
        List<String> nameInfo = name.stringify();
        System.out.println();
        System.out.println("============================");
        System.out.println(" Name");
        System.out.println("============================");
        for(String info : nameInfo){
            System.out.println("[" + i++ + "] " + info);
        }
        System.out.println("[" + i++ + "] Save");
        System.out.println("[" + i++ + "] Cancel");
        System.out.println("----------------------------");
        return InputValidator.getInputMenu(" Option: ", 7);
    
    }
    
    /* int choice: 1 - street, 2 - brgy, 3 - municipality, 4 - country,  5 - zipcode */
    public static Address addressMenu(Address address){
        int choice = 1;   
        int i; 
        List<String> addressInfo;
        String input;
        
        do{    
            choice = printAddressMenu(address);
            switch(choice) {
                case 1:
                    input = InputValidator.getInputStr("Enter street: ", 30);
                    address.setStreet(input);
                    break;
                case 2:
                    // CONTINUE HERE
                    input = InputValidator.getInputStr("Enter brgy: ", 30);
                    address.setBrgy(input);
                    break;
                case 3:
                    input = InputValidator.getInputStr("Enter municipality: ", 30);
                    address.setMunicipality(input);
                    break;
                case 4:
                    input = InputValidator.getInputStr("Enter country: ", 30);
                    address.setCountry(input);
                    break;
                case 5:
                    input = InputValidator.getInputStr("Enter zipcode: ", 30);
                    address.setZipcode(input);
                    break;
                case 6:
                    return address;
                case 7:
                    return null;
            }
        } while (choice != 6 && choice != 7);
        
        return null;
    }
    
    public static int printAddressMenu(Address address){
        int i = 1;
         
        List<String> addressInfo = address.stringify();
        System.out.println();
        System.out.println("============================");
        System.out.println(" Address");
        System.out.println("============================");
        for(String info : addressInfo){
            System.out.println("[" + i++ + "] " + info);
        }
        System.out.println("[" + i++ + "] Save");
        System.out.println("[" + i++ + "] Cancel");
        System.out.println("----------------------------");
        return InputValidator.getInputMenu(" Option: ", 7);
    
    }
    
    public static Set contactsMenu(Set contactsInput, int empId){
        int choice = 1;   
        int i; 
        Contact contactInput;
        String input;
        
        do{    
            choice = printContactsMenu(contactsInput);
            switch(choice) {
                case 1:
                    contactsInput = addNewContact(contactsInput, empId);
                    break;
                case 2:
                    contactsInput = editContact(contactsInput);
                    break;
                case 3:
                    contactsInput = deleteContact(contactsInput);
                    break;
                case 4:
                    return contactsInput;
                case 5:
                    return null;
            }
        } while (choice != 4 && choice != 5);
        
        return null;
    }
    
    public static int printContactsMenu(Set<Contact> contactsInput){        
        List<String> contactInfo = new ArrayList<String>();
         
        for(Contact contact : contactsInput){
            contactInfo.add(contact.stringify());    
        } 
        System.out.println();
        System.out.println("============================");
        System.out.println(" Contacts");
        System.out.println("============================");
        for(String info : contactInfo){
            System.out.println(info);
        }
        System.out.println("----------------------------");
        System.out.println("[1] Add");
        System.out.println("[2] Edit");
        System.out.println("[3] Delete");
        System.out.println("[4] Save");
        System.out.println("[5] Cancel");
        System.out.println("----------------------------");
        return InputValidator.getInputMenu(" Option: ", 5);
    }
    
    public static Set addNewContact(Set contactsInput, int empId){
        int choice = 1;
        Contact newContact;
        String newContactStr;
        
        do{    
            choice = addNewContactMenu(contactsInput);
            switch(choice) {
                case 1:
                    newContactStr = InputValidator.getInputMobile();
                    newContact = new Contact("Mobile", newContactStr, empId, true);
                    
                    if(contactsInput.contains(newContact)){
                        System.out.println("Contact already exists");
                        continue;
                    }
                    
                    contactsInput.add(newContact);
                    break;
                case 2:
                    newContactStr = InputValidator.getInputEmail();
                    newContact = new Contact("Email", newContactStr, empId, true);
                    
                    if(contactsInput.contains(newContact)){
                        System.out.println("Email already exists");
                        continue;
                    }
                    
                    contactsInput.add(newContact);
                    break;
                case 3:
                    newContactStr = InputValidator.getInputPhone();
                    newContact = new Contact("Phone", newContactStr, empId, true);
                    
                    if(contactsInput.contains(newContact)){
                        System.out.println("Phone already exists");
                        continue;
                    }
                    
                    contactsInput.add(newContact);
                    break;
                case 4:
                    return contactsInput;
                case 5:
                    return null;
            }
        } while (choice != 4 && choice != 5);
        
        return null; 
    }
    
    public static int addNewContactMenu(Set<Contact> contactsInput){  
    
        List<String> contactInfo = new ArrayList<String>();
        
        for(Contact contact : contactsInput){
            contactInfo.add(contact.stringify());    
        }
         
        System.out.println();
        System.out.println("============================");
        System.out.println(" Contacts");
        System.out.println("============================");
        for(String info : contactInfo){
            System.out.println(info);
        }
        System.out.println("----------------------------");
        System.out.println("[1] Add Mobile");
        System.out.println("[2] Add Email");
        System.out.println("[3] Add Phone");
        System.out.println("[4] Save");
        System.out.println("[5] Back");
        System.out.println("----------------------------");
        return InputValidator.getInputMenu(" Option: ", 5);
    
    }
    
    public static Set editContact(Set<Contact> contactsInput){
        int i = 1;
        int choice;       
        List<Contact> contactList = new ArrayList<Contact>();
        Contact temp;
        String input = "";
         
        System.out.println();
        System.out.println("============================");
        System.out.println(" Contacts");
        System.out.println("============================");
        for(Contact contact : contactsInput){
            System.out.print("[" + i++ + "] ");
            System.out.println(contact.stringify());
            contactList.add(contact);    
        }
        System.out.println("[" + i + "] Cancel");
        choice = InputValidator.getInputMenu(" Select contact to edit: ", i);
        
        if(choice == i){
            return contactsInput;
        }
        temp = contactList.get(choice-1);
        contactsInput.remove(temp);
        
        switch(temp.getContactType()){
            case "Mobile":
                System.out.println("New mobile no.: ");
                input = InputValidator.getInputMobile();
                break;
            case "Email":
                System.out.println("New email: ");
                input = InputValidator.getInputEmail();
                break;
            case "Phone":
                System.out.println("New phone no.: ");
                input = InputValidator.getInputPhone();
                break;
        }
        
        temp.setContactDetails(input);
        contactsInput.add(temp);
        return contactsInput;
    }
    
    public static Set deleteContact(Set<Contact> contactsInput){
        int i = 1;
        int choice;       
        List<Contact> contactList = new ArrayList<Contact>();
         
        System.out.println();
        System.out.println("============================");
        System.out.println(" Contacts");
        System.out.println("============================");
        for(Contact contact : contactsInput){
            System.out.print("[" + i++ + "] ");
            System.out.println(contact.stringify());
            contactList.add(contact);    
        }
        System.out.println("[" + i + "] Cancel");
        choice = InputValidator.getInputMenu(" Select contact to delete: ", i);
        
        if(choice == i){
            return contactsInput;
        }
        contactsInput.remove(contactList.get(choice-1));
        return contactsInput;
    }
    
    public static Employee searchEmployee(EmployeeService es, String type) {
        List<Employee> empList;
        Employee temp = null;
        int i = 1, choice = 1;
        String input;
        
        input = InputValidator.getInputStr("Enter name of employee to " + type + ": ", 30);
        empList = es.searchEmployeeByName(input);
        if(empList == null){
            System.out.println("No employee found.");
            return temp;
        }
        else if(empList.size() > 1){
            App.printEmployees(empList, true);
            choice = InputValidator.getInputMenu(" Enter the employee no. to be " + type + "ed: ", empList.size());
        }
        temp = empList.get(choice-1);
        
        return temp;
    }
    
    public static Set rolesMenu(EmployeeService es, Set rolesInput, int empId){
        int choice = 1;   
        int i; 
        Role roleInput;
        String input;
        Set<Role> temp = null;
        
        do{    
            choice = printRolesMenu(rolesInput);
            switch(choice) {
                case 1:
                    temp = addNewRole(es, rolesInput, empId);
                    rolesInput = temp == null ? rolesInput : temp;
                    break;
                case 2:
                    break;
                case 3:
                    return rolesInput;
                case 4:
                    return null;
            }
        } while (choice != 3 && choice != 4);
        
        return null;
    }
    
    public static int printRolesMenu(Set<Role> rolesInput){        
        List<String> roleInfo = new ArrayList<String>();
         
        for(Role role : rolesInput){
            roleInfo.add(role.getName());    
        } 
        System.out.println();
        System.out.println("============================");
        System.out.println(" Roles");
        System.out.println("============================");
        for(String info : roleInfo){
            System.out.println(info);
        }
        System.out.println("----------------------------");
        System.out.println("[1] Add");
        System.out.println("[2] Delete");
        System.out.println("[3] Save");
        System.out.println("[4] Cancel");
        System.out.println("----------------------------");
        return InputValidator.getInputMenu(" Option: ", 4);
    }
    
    public static Set addNewRole(EmployeeService es, Set<Role> rolesInput, int empId){
        int choice = 1;
        Contact newRole;
        List<Role> availableRoles = null;
        int i = 1;
        
        availableRoles = es.getAvailableRoles(rolesInput);
         
        System.out.println();
        System.out.println("============================");
        System.out.println(" Current Roles");
        System.out.println("============================");
        
        for(Role role : rolesInput){
            System.out.println("\t" + role.getName());
        }
        
        System.out.println("----------------------------");
        
        if(availableRoles != null){
            for(Role role : availableRoles){
                System.out.println("[" + i++ + "] " + role.getName());
            }
        }
        
        System.out.println("[" + i++ + "] Cancel");
        System.out.println("----------------------------");
        choice = InputValidator.getInputMenu(" Add what role: ", i-1);
        if(choice != i-1){
            rolesInput.add(availableRoles.get(choice-1));
            return rolesInput;
        }
        
        return null;
    }
    
    public static void addNewRoleType(EmployeeService es){
        List<Role> allRoles = es.listRoles();
        String input = "";
        int choice = 1;
        Role newRole;
        boolean accepted = false;
        
        do{
            printRoles(allRoles, false);
        
            System.out.println("============================");
            System.out.println(" [1] Add Role");
            System.out.println(" [2] Cancel");
            System.out.println("============================");
            choice = InputValidator.getInputMenu(" Option: ", 2);
            
            switch(choice){
                case 1: 
                    while(!accepted){
                        input = InputValidator.getInputStr(" New Role: ", 50);
                        for(Role role : allRoles){
                            if(InputValidator.isEqual(role.getName(), input)){
                                System.out.println("Role already exists.");
                            }
                        }
                        newRole = new Role(input, true);
                        es.addRole(newRole);
                        allRoles.add(newRole);
                        break;
                    }
                case 2:
                    break;
            }
        }while(choice != 2);        
    }
    
    public static void editRoleType(EmployeeService es){
        List<Role> allRoles = es.listRoles();
        String input = "";
        Role tempRole = null;
        int choice = 1, roleCount = allRoles.size();
        boolean accepted = false;
        
        do{
            printRoles(allRoles, true);
        
            System.out.println("============================");
            System.out.println(" [" + (roleCount + 1)+ "] Cancel");
            System.out.println("============================");
            choice = InputValidator.getInputMenu(" Role to edit: ", roleCount + 1);
            if(choice == roleCount+1){
                break;
            }
            tempRole = allRoles.get(choice-1);
            while(!accepted){
                input = InputValidator.getInputStr(" Edit Role to: ", 50);
                for(Role role : allRoles){
                    if(InputValidator.isEqual(role.getName(), input)){
                        System.out.println("Role already exists.");
                    }
                }
                tempRole.setName(input);
                allRoles.set(choice-1, tempRole);
                es.addRole(tempRole);
                break;
            }
            
        } while (choice != roleCount + 1);       
    }
    
    public static void printRoles(List<Role> roles, boolean isMenu){
        int i = 1;
        
        
        System.out.println();
        System.out.println("============================");
        System.out.println(" Current Roles");
        System.out.println("============================");
        
        for(Role role : roles){
            if(isMenu){
                System.out.print("[" + i++ + "] ");
            }
            System.out.println(role.getName());
        }
        
        System.out.println();
    }
    
    public static void deleteRoleType(EmployeeService es){
        List<Role> allRoles = es.listRoles();
        String input = "";
        Role tempRole = null;
        int choice = 1, roleCount = allRoles.size();
        boolean accepted = false;
        
        do{
            printRoles(allRoles, true);
        
            System.out.println("============================");
            System.out.println(" [" + (roleCount + 1)+ "] Cancel");
            System.out.println("============================");
            choice = InputValidator.getInputMenu(" Role to delete: ", roleCount + 1);
            if(choice == roleCount+1){
                break;
            }
            
            tempRole = allRoles.get(choice-1);
            tempRole.setEmployees(new HashSet<Employee>());
            es.updateRole(tempRole);
            es.deleteRole(tempRole);
            break;   
        } while (choice != roleCount + 1);   
    }
    
    public static void deleteEmployee(EmployeeService es, Employee emp){
        int i = 1;
        int choice = 1;
        Set<Role> roles;
        
        System.out.println();
        System.out.println(" Are you sure you want to delete " + emp.getName().getFullname() + "?");
        System.out.println(" [1] Yes");
        System.out.println(" [2] No");
        choice = InputValidator.getInputMenu(" Option: ", 2);
        
        switch(choice){
            case 1:
                emp.setRoles(new HashSet<Role>());
                es.updateEmployee(emp);
                es.deleteEmployee(emp);
                break;
            case 2:
                break;
        }
        System.out.println();
    }
}

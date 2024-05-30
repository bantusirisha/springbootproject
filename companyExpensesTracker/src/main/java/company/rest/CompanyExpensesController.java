package company.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import company.entities.Category;
import company.entities.CategoryRepo;
import company.entities.Department;
import company.entities.DepartmentRepo;
import company.entities.Expenditure;
import company.entities.PaymentMode;
import company.entities.PaymentModeRepo;
import company.repositories.ExpenditureRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class CompanyExpensesController {
	
	@Autowired
	ExpenditureRepo expenditureRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	DepartmentRepo departmentRepo;

	@Autowired
	PaymentModeRepo paymentModeRepo;

// get all expenditures
	@CrossOrigin
	@GetMapping("/expenditures")
	@Operation(summary = " Get all expenditures", description = "Retrieve a list of all expenditures")
	@ApiResponse(responseCode = "200", description = "Expenditures retrieved successfully")
	@ApiResponse(responseCode = "404", description = "There is no data in expenditures")
	@ApiResponse(responseCode = "500", description = "There is no table for expenditures")
	public List<Expenditure> getAllExpenditures() {
		var expenses = expenditureRepo.findAll();
		if (expenses.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no data in expenditures");
		return expenses;
	}

// 1.1 Add a new expenditure
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/expenditures/add")
	@Operation(summary = " 1.1 Add a new expenditure", description = "Create and add a new expenditure")
	@ApiResponse(responseCode = "200", description = "Expenditure added successfully")
	public Expenditure addExpenditure(@Valid @RequestBody Expenditure expenditure) {
		try {
			expenditureRepo.save(expenditure);
			return expenditure;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

// 1.2 Delete an expenditure
	@DeleteMapping("/expenditures/delete/{id}")
	@Operation(summary = " 1.2 Delte an expenditure", description = "Remove an expenditure using its ID")
	@ApiResponse(responseCode = "200", description = "Expenditure deleted successfully")
	@ApiResponse(responseCode = "404", description = "Expenditure with the given ID Not Found")
	public void deleteOneExpenditure(@PathVariable("id") int id) {
		Optional<Expenditure> optExpenditure = expenditureRepo.findById(id);
		if (optExpenditure.isPresent()) {
			expenditureRepo.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with given code id Not Found!");
		}
	}

// 1.3 Update an expenditure by ID
	@PutMapping("/expenditures/update/{id}")
	@Operation(summary = " 1.3 Update an expenditure by ID", description = "Modify an existing expenditure using its ID")
	@ApiResponse(responseCode = "200", description = "Expenditure updated successfully")
	@ApiResponse(responseCode = "404", description = "Expenditure id Not Found")
	public void updateExpenditure(@PathVariable("id") int id, @Valid @RequestBody Expenditure expenditure) {
		var optExpenditure = expenditureRepo.findById(id);
		if (optExpenditure.isPresent()) {
			var exp = optExpenditure.get();
			exp.setCatcode(expenditure.getCatcode());
			exp.setDeptcode(expenditure.getDeptcode());
			exp.setAmount(expenditure.getAmount());
			exp.setExpdate(expenditure.getExpdate());
			exp.setAuthorizedby(expenditure.getAuthorizedby());
			exp.setDescription(expenditure.getDescription());
			exp.setPaymentmodecode(expenditure.getPaymentmodecode());
			exp.setRemarks(expenditure.getRemarks());
			expenditureRepo.save(exp);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expenditure Id Not Found!");
	}

// Get all payment modes
	@GetMapping("/paymentModes")
	@Operation(summary = " Get all payment modes", description = "Retrieve a list of all payment modes")
	@ApiResponse(responseCode = "200", description = "Payment Modes retrieved successfully")
	@ApiResponse(responseCode = "404", description = "There is no data in paymentModes")
	@ApiResponse(responseCode = "500", description = "There is no table for paymentModes")

	public List<PaymentMode> getAllPaymentmodes() {
		var payments = paymentModeRepo.findAll();
		if (payments.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no data in paymentModes");
		return payments;
	}

// 2.1 Add a new payment mode
	@PostMapping("/paymentModes/add")
	@Operation(summary = " 2.1 Add a new payment mode", description = "Add a new payment mode to the system")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Payment mode added successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request, Payment mode already exists") })
	public PaymentMode addNewPaymentModes(@Valid @RequestBody PaymentMode paymentmode) {
		try {
			var optPaymentMode = paymentModeRepo.findById(paymentmode.getPaymentmodecode());
			if (optPaymentMode.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment mode Already Present");
			}
			paymentModeRepo.save(paymentmode);
			return paymentmode;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

// 2.2 Delete a payment mode by code
	@DeleteMapping("/paymentModes/delete/{code}")
	@Operation(summary = " 2.2 Delete a payment mode by code", description = "Delete a payment mode by its code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Payment mode deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Payment code not found") })
	public void deleteOneMode(@PathVariable("code") String code) {
		var optPayment = paymentModeRepo.findById(code);
		if (optPayment.isPresent())
			paymentModeRepo.deleteById(code);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment code Not Found!");
	}

// 2.3 Update a payment mode by code
	@PutMapping("/paymentModes/update/{code}")
	@Operation(summary = " 2.3 Update a payment mode by code", description = "Update the details of a payment mode by its code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Payment mode updated successfully"),
			@ApiResponse(responseCode = "404", description = "Payment code not found") })
	public void updatePaymentMode(@PathVariable("code") String code, @Valid @RequestBody PaymentMode mode) {
		var optPayment = paymentModeRepo.findById(code);
		if (optPayment.isPresent()) {
			var paymentMode = optPayment.get();
			paymentMode.setPaymentmodecode(mode.getPaymentmodecode());
			paymentMode.setPaymentName(mode.getPaymentName());
			paymentMode.setPaymentRemarks(mode.getPaymentRemarks());
			paymentModeRepo.save(paymentMode);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Name Not Found!");
	}

// Get all departments
	@GetMapping("/departments")
	@Operation(summary = "Get all departments", description = "Retrieve a list of all departments")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Departments retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "There is no data in departments"),
			@ApiResponse(responseCode = "500", description = "There is no table for departments") })
	public List<Department> getAllDepartments() {
		var departments = departmentRepo.findAll();
		if (departments.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no data in departments");
		return departments;

	}

// 3.1 Add a new department
	@PostMapping("/departments/add")
	@Operation(summary = " 3.1 Add a new department", description = "Add a new department to the system")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Department added successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request, Department code already exists") })
	public Department addNewDepartment(@Valid @RequestBody Department department) {
		try {
			var optDepartment = departmentRepo.findById(department.getDeptcode());
			if (optDepartment.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department Code Already Present");
			}
			departmentRepo.save(department);
			return department;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

// 3.2 Delete a department by code
	@DeleteMapping("/departments/delete/{deptCode}")
	@Operation(summary = " 3.2 Delete a department by code", description = "Delete a department by its code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Department deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Department code not found") })

	public void deleteOneDepartment(@PathVariable("deptCode") String deptCode) {
		Optional<Department> optDepartment = departmentRepo.findById(deptCode);
		if (optDepartment.isPresent()) {
			departmentRepo.deleteById(deptCode);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with code " + deptCode + " Not Found!");
		}
	}

// 3.3 Update a department by code
	@PutMapping("/departments/update/{deptCode}")
	@Operation(summary = " 3.3 Update a department by code", description = "Update the details of a department by its code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Department updated successfully"),
			@ApiResponse(responseCode = "404", description = "Department code not found") })
	public void updatePaymentMode(@PathVariable("deptCode") String deptCode,
			@Valid @RequestBody Department department) {
		var optDepartment = departmentRepo.findById(deptCode);
		if (optDepartment.isPresent()) {
			var dept = optDepartment.get();
			dept.setDeptcode(department.getDeptcode());
			dept.setDeptname(department.getDeptname());
			dept.setHod(department.getHod());
			departmentRepo.save(dept);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department Code Not Found!");
	}

// 4.1 Add a new category
	@PostMapping("/categories/add")
	@Operation(summary = " 4.1 Add a new category", description = "Create and add a new category")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Category added successfully"),
			@ApiResponse(responseCode = "400", description = "Category Code Already Present"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	public Category addNewCategory(@Valid @RequestBody Category category) {
		try {
			var optCategory = categoryRepo.findById(category.getCatCode());
			if (optCategory.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category Code Already Present");
			}
			categoryRepo.save(category);
			return category;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

// 4.2 Delete a category by code
	@DeleteMapping("/categories/delete/{catcode}")
	@Operation(summary = " 4.2 Delete a category by code", description = "Remove a category using its code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Category Code Not Found") })
	public void deleteOneCategory(@PathVariable("catcode") String catcode) {
		var optCategory = categoryRepo.findById(catcode);
		if (optCategory.isPresent())
			categoryRepo.deleteById(catcode);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Code Not Found!");
	}

// 4.4 Update a category by code
	@PutMapping("/categories/update/{catcode}")
	@Operation(summary = " 4.4 Update a category by code", description = "Modify an existing category using its code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Category updated successfully"),
			@ApiResponse(responseCode = "404", description = "Category Code Not Found") })
	public void updateCategory(@PathVariable("catcode") String catcode, @Valid @RequestBody Category category) {
		var optCategory = categoryRepo.findById(catcode);
		if (optCategory.isPresent()) {
			var cat = optCategory.get();
			cat.setCatCode(category.getCatCode());
			cat.setCatName(category.getCatName());
			categoryRepo.save(cat);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Code Not Found!");
	}

// 5. List all expenses by category using pagination and sort by id	
	@GetMapping("/expensesByCategory/{catCode}")
	@Operation(summary = " 5. List all expenses by category using pagination and sort by id", description = "Retrieve expenses by a given category code and sort by id using pagination")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expenses found by the given category code"),
			@ApiResponse(responseCode = "404", description = "No expenses for the given Category Code") })
	public List<Expenditure> getExpensesByCategory(@PathVariable String catcode) {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));
		List<Expenditure> expenses = expenditureRepo.findExpensesByCategory(catcode, pageable);
		if (expenses.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expenses for given Category Code!");
		}
		return expenses;
	}

// 6. List all expenses by payment mode using pagination and sort by id
	@GetMapping("/expensesByPaymentMode/{paymentmodecode}")
	@Operation(summary = " 6. List all expenses by payment mode using pagination and sort by id", description = "Retrieve expenses by a given payment mode code and sort by id using pagination")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expenses found by the given payment mode code"),
			@ApiResponse(responseCode = "404", description = "No expenses for the given PaymentModeCode") })
	public List<Expenditure> getExpensesByPaymentMode(@PathVariable String paymentmodecode) {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));
		List<Expenditure> expenses = expenditureRepo.findExpensesByPaymentMode(paymentmodecode, pageable);
		if (expenses.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expenses for given Payment Mode Code!");
		}
		return expenses;
	}

// 7. List all expenses between two given dates using pagination & sort by date in desc
	@GetMapping("/expensesBetweenDates/sortByDate/{startDate}/{endDate}")
	@Operation(summary = " 7. List all expenses between two given dates using pagination & sort by date in desc", description = "Retrieve expenses between two dates and sorted by date in descending using pagination")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expenses found between the given dates, sorted by date"),
			@ApiResponse(responseCode = "404", description = "No expenditures found between the given dates") })
	public List<Expenditure> getExpensesBetweenDates(@PathVariable("startDate") LocalDate startDate,
			@PathVariable("endDate") LocalDate endDate) {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("expdate").descending());
		List<Expenditure> expenses = expenditureRepo.findExpensesBetweenDatesSortedByDate(startDate, endDate, pageable);
		if (expenses.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expenditures found between the given dates!");
		}
		return expenses;
	}

// 8. List expenses summary(total amount)for each category in a given month
	@GetMapping("/expenditures/category{selectedMonth}")
	@Operation(summary = " 8. List expenses summary(total amount)for each category in a given month", description = "Retrieve the total expenses by category in the specified month")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Total expenses by category retrieved successfully") })
	public List<Object[]> getExpenseSummaryByCategoryInMonth(@RequestParam("selectedMonth") int selectedMonth) {
		var expenses = expenditureRepo.getTotalExpensesByCategoryInMonth(selectedMonth);
		try {
			if (expenses.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			return expenses;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

// 9. List expenses of a department b/w given dates
	@GetMapping("/expensesOfDept/dates")
	@Operation(summary = " 9. List expenses of a department b/w given dates", description = "Retrieve expenses for a specific department and date range")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expenses found for the specified department and date range"),
			@ApiResponse(responseCode = "404", description = "No expenses found for the specified department and date range") })
	public List<Expenditure> getExpensesByDepartmentAndDateRange(@RequestParam("deptcode") String deptcode,
			@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
		List<Expenditure> expenditures = expenditureRepo.findByDeptcodeAndExpdateBetween(deptcode, startDate, endDate);
		if (expenditures.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No expenses found for the specified department and date range");
		}
		return expenditures;
	}

// 10. List all expenses authorized by employee name
	@GetMapping("/getExpensesByEmployee")
	@Operation(summary = " 10. List all expenses authorized by employee name", description = "Retrieve expenses authorized by a specific employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expenses found for the specified employee"),
			@ApiResponse(responseCode = "404", description = " No Expenses found for the specified employee") })
	public List<Expenditure> findByAuthorizedbyEmployee(@RequestParam("authorizedby") String authorizedby) {
		var expenses = expenditureRepo.findByAuthorizedby(authorizedby);
		if (expenses.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expenses found for the given employee name");
		return expenditureRepo.findByAuthorizedby(authorizedby);

	}

// 11. List all expenses where description containing the given string
	@GetMapping("/getExpenses/DescriptionContainingString")
	@Operation(summary = " 11. List all expenses where description containing the given string", description = "Retrieve expenses with descriptions containing the specified string")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expenses found with descriptions containing the specified string"),
			@ApiResponse(responseCode = "404", description = "Given string is not present in the description") })
	public List<Expenditure> findByDescriptionContaining(@RequestParam("StrToSearch") String StrToSearch) {
		var expenses = expenditureRepo.findByDescriptionContaining(StrToSearch);
		if (expenses.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "given string is not present in the description");
		return expenses;
	}

// 12. List all expenses where amount is b/w the given min & max values
	@GetMapping("/expenditures/amount")
	@Operation(summary = " 12. List all expenses where amount is b/w the given min & max values", description = "Retrieve expenditures within a specified range of amounts")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expenditures found within the range of amount"),
			@ApiResponse(responseCode = "404", description = "No expenditures found in the range of amount") })
	public List<Expenditure> searchTitlesByAmount(@RequestParam("min") Double min, @RequestParam("max") Double max) {
		List<Expenditure> expenditures = expenditureRepo.findByAmountBetween(min, max);
		if (expenditures.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expenditures found in the range of amount");
		}
		return expenditures;
	}

// 13. Get all categories
//	@CrossOrigin()
	@GetMapping("/categories")
	@Operation(summary = " 13. Get all categories", description = "Retrieve a list of all categories")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "There is no data in catgories"),
			@ApiResponse(responseCode = "500", description = "There is no table for categories") })
	public List<Category> getAllCategories() {
		var categories = categoryRepo.findAll();
		if (categories.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no data in catgories");
		return categories;
	}

// 14. List department and total amount spent on department
	@GetMapping("/expenditures/department/totalAmountOnDepartment")
	@Operation(summary = " 14. List department and total amount spent on department", description = "Retrieve the total amount of expenditures for each department")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Total amount of each department retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "No departments found!") })
	public List<Object[]> getTotalAmountOfEachDepartment() {
		var expenses = expenditureRepo.getTotalAmountOfEachDepartment();
		if (expenses.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no departments in expenditures");
		return expenses;
	}

// 15. List category and total amount spent on category
	@GetMapping("/expenditures/category/totalAmountOfCategory")
	@Operation(summary = " 15. List category and total amount spent on category", description = "Retrieve the total amount of expenditures for each category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Total amount of each category retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "No categories found!") })
	public List<Object[]> getTotalAmountOfEachCategory() {
		var expenses = expenditureRepo.getTotalAmountOfEachCategory();
		if (expenses.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no categories in expenditures");
		return expenses;
	}
	
	
	
	
	
	// examples
	@GetMapping("/exp/cat/{catCode}")
	public List<Expenditure> getCat(@PathVariable("catCode") String catCode){
		var expenses=expenditureRepo.findByCategory(catCode);
		return expenses;
	}
	
	
}
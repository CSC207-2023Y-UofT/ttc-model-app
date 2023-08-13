package interface_adapter.controller;

import app_business.boundary.IEmployeeInteractor;
import app_business.common.EmployeeType;
import app_business.dto.EmployeeDTO;
import entity.model.train.TrainRole;

import java.util.List;
import java.util.Optional;

/**
 * The EmployeeController class represents the controller for the employee use cases.
 */
public class EmployeeController {
    /**
     * The interactor used by the controller to interact with the domain layer.
     */
    private final IEmployeeInteractor interactor;

    public IEmployeeInteractor getInteractor() {
        return interactor;
    }

    /**
     * Constructs a new EmployeeController with the given interactor.
     *
     * @param interactor the interactor
     */
    public EmployeeController(IEmployeeInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Logs in an employee with the given staff number.
     *
     * @param staffNumber the staff number
     * @return the employee if found, otherwise empty
     */
    public Optional<EmployeeDTO> login(int staffNumber) {
        return interactor.find(staffNumber);
    }

    /**
     * Registers an employee with the given name and type. The employee's staff number is generated by the interactor.
     *
     * @param name the name
     * @param type the type
     * @return the employee
     */
    public EmployeeDTO registerEmployee(String name, EmployeeType type) {
        return interactor.registerEmployee(name, type, interactor.idGenerator());  // idGenerator has default value 999999 from overloading
    }

    /**
     * Assigns an employee to a train with the given role.
     *
     * @param staffNumber the staff number of the employee
     * @param trainName   the exact name of the train
     * @param role        the role of the employee
     */
    public void assignEmployee(int staffNumber, String trainName, TrainRole role) {
        interactor.assignJob(staffNumber, trainName, role);
    }

    /**
     * Unassigns an employee.
     * @param staffNumber the staff number of the employee.
     */
    public void unassignEmployee(int staffNumber) {
        interactor.unassign(staffNumber);
    }

    /**
     * Removes an employee from the system.
     *
     * @param staffNumber the staff number of the employee
     */
    public void removeEmployee(int staffNumber) {
        interactor.removeEmployee(staffNumber);
    }

    /**
     * Finds an employee with the given staff number.
     *
     * @param staffNumber the staff number
     * @return the employee if found, otherwise empty
     */
    public Optional<EmployeeDTO> find(int staffNumber) {
        return interactor.find(staffNumber);
    }

    /**
     * Get the list of employees assigned to a train
     */
    public List<EmployeeDTO> byAssignment(String trainName) {
        return interactor.getAssignedEmployees(trainName);
    }

    /**
     * Gets all employees in the system.
     *
     * @return the list of employees in Data Transfer Object form
     */
    public List<EmployeeDTO> getEmployees() {
        return interactor.getEmployees();
    }
}

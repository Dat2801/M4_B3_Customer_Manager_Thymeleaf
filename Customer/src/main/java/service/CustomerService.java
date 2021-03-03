package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    public static Map<Integer,Customer> customers = new HashMap<>();

    static {
        customers.put(1,new Customer(1, "Dat", "Dattb28@gmail.com", "Thai Binh"));
        customers.put(2,new Customer(2, "Tu", "Tu2002@gmail.com", "Nghe An"));
        customers.put(3,new Customer(3, "Trung", "TrungHp@gmail.com", "Hai Phong"));
        customers.put(4,new Customer(4, "Trung", "TrungHp@gmail.com", "Hai Phong"));
        customers.put(5,new Customer(5, "Tung", "Tung@gmail.com", "Hai Duong"));
        customers.put(6,new Customer(6, "tung123", "Tung123@gmail.com", "Hai Duong 2"));

    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer edit(int id, Customer customer) {
        customers.put(id, customer);
        return customer;
    }

    @Override
    public Customer create( Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> listName = new ArrayList<>();
        for (Customer customer : customers.values()) {
            if (customer.getName().contains(name)) {
                listName.add(customer);
            }
        }
        return listName;
    }

    @Override
    public void delete(int id) {
        customers.remove(id);
    }
}

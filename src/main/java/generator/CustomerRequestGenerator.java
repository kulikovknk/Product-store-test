package generator;

import dto.CustomerRequest;
import net.datafaker.Faker;

public class CustomerRequestGenerator {

    public CustomerRequest getCustomerRequest(Boolean random) {

        CustomerRequest customerRequest = new CustomerRequest();

        if (random) {
            Faker faker = new Faker();

            customerRequest.setUsername(faker.name().username());
            customerRequest.setPassword(faker.internet().password());
        }

        return customerRequest;
    }

    public CustomerRequest getCustomerRequest() {

        CustomerRequest customerRequest = new CustomerRequest();

        // хардкод при создании учетных данных - плохая практика
        // в данном случае применяю ее, чтобы не засорять тестовый сайт сгенерированными учетками
        // так как нет информации как можно удалить учетную запись из базы
        customerRequest.setUsername("rikki.nienow");
        customerRequest.setPassword("29p2adfl380");

        return customerRequest;
    }
}

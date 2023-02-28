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
        } else {
            // хардкод при создании тестовых данных - плохая практика
            // в данном случае нет информации как можно удалить учетную запись из базы
            // поэтому использую готовые данные, чтобы не засорять тестовый сайт сгенерированными учетками
            customerRequest.setUsername("rikki.nienow");
            customerRequest.setPassword("29p2adfl380");
        }

        return customerRequest;
    }
}

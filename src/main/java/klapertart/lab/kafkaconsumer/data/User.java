package klapertart.lab.kafkaconsumer.data;

import lombok.Data;

/**
 * @author kurakuraninja
 * @since 20/02/23
 */

@Data
public class User {
    private String id;
    private String firstName;
    private String lastName;
}

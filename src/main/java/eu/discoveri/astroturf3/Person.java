/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.discoveri.astroturf3;

import java.util.UUID;

/**
 *
 * @author chrispowell
 */
public class Person
{
    java.util.UUID ID;
    String identifier;
    String name;

    public Person(UUID ID, String identifier, String name) {
        this.ID = ID;
        this.identifier = identifier;
        this.name = name;
    }

    public UUID getID() {
        return ID;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }
}

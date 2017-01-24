/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.modules.simple.dom.petclinic;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.util.ObjectContracts;

import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(
        identityType=IdentityType.DATASTORE,
        schema = "petclinic"
)
@DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column="id")
@Version(
        strategy= VersionStrategy.DATE_TIME,
        column="version")
@Queries({
        @Query(
                name = "findByName",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.petclinic.Owner "
                        + "WHERE firstName.toLowerCase().indexOf(:name.toLowerCase()) >= 0 || "
                        + "lastName.toLowerCase().indexOf(:name.toLowerCase()) >=0 ")
})
@Unique(name="Owner_firstName_lastName_UNQ", members = {"firstName", "lastName"})
@DomainObject()
public class Owner implements Comparable<Owner> {

    public Owner(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String title(){
        return getFirstName().concat(" ").concat(getLastName());
    }

    @Column(allowsNull = "false")
    @Getter @Setter
    private String firstName;

    @Column(allowsNull = "false")
    @Getter @Setter
    private String lastName;

    @Action(semantics = SemanticsOf.IDEMPOTENT)
    public Owner updateName(
            final String firstName,
            final String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        return this;
    }

    public String default0UpdateName() {
        return getFirstName();
    }

    public String default1UpdateName() { return getLastName(); }

    @Override
    public String toString() {
        return ObjectContracts.toString(this, "firstName", "lastName");
    }

    @Override
    public int compareTo(final Owner other) {
        return ObjectContracts.compare(this, other, "lastName", "firstName");
    }


}
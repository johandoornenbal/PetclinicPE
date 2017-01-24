package domainapp.modules.simple.fixture.petclinic;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import domainapp.modules.simple.dom.petclinic.Owner;
import domainapp.modules.simple.dom.petclinic.OwnerRepository;

public abstract class OwnerAbstract extends FixtureScript {

    protected Owner createOwner(
            final String firstName,
            final String lastName,
            final ExecutionContext fixtureResults
    ){
        Owner owner = ownerRepository.create(
            firstName,
            lastName
        );
        return fixtureResults.addResult(this, owner);
    }

    @Inject
    protected OwnerRepository ownerRepository;

}

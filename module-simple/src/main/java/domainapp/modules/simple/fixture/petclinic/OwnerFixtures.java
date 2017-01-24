package domainapp.modules.simple.fixture.petclinic;

import domainapp.modules.simple.dom.petclinic.Owner;

public class OwnerFixtures extends OwnerAbstract {

   @Override
   protected void execute(final ExecutionContext executionContext){

        Owner Bill = createOwner("Bill", "Gates", executionContext);
        Owner Elon = createOwner("Elon", "Musk", executionContext);
        Owner Steve = createOwner("Steve", "Jobs", executionContext);

   }

}

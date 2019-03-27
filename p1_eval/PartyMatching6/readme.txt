~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
README
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Files:
data_types/Person.java
runner/Main.java
runner/ParticipantGenerator.java
runner/Party.java
~~~~~
Person.java:
Contains the Person class, which represents a superhero or sidekick.

variables:
String name: the name of the person
boolean type: true for superhero, false for sidekick
String partnerName: name of the intended partner

methods:
String getName(): returns name
setName(String s): changes name to s
boolean isType(): return type, true for superhero
setType(boolean t): change type to t
String getPartnerName(): return partnerName
setPartnerName(String p): set partnerName to p

constructor:
Person(String name, boolean type, string partnerName)
~~~~~
Main.java:
Creates an ArrayList, list, of PersonGenerator objects, which will each run in a different
thread. Then, calls start() on all objects in the array in another for loop. A third
for loop calls join(1000) on all threads in list to time them out, as the threads
will not necessarily end on their own; they only end when they find a partner and
they are not guaranteed to find a partner.
~~~~~
Party.java
Contains a list of Person objects called partyList. This contains all people
currently in the party. Access is controlled by lock (a lock). There is a condition
used with lock called isReady, which signals that no other thread is accessing
partyList, as well as the boolean variable isBeingAccessed which is set to true
when a thread is acting on partyList and false otherwise. This condition and
boolean are used to make sure only one thread accesses partyList at a time.

methods:
enter(Person person): First, this method attempts to gain the lock. Once it does,
it adds person to partyList. After that, it loops through everyone in partyList
to find the firstinstance of someone who matches person's intended partner, if
one exists. If a partner is found, both the person and partner are removed from
partyList. Finally,the lock is released.
~~~~~
ParticipantGenerator.java:
ParticipantGenerator implements Runnable to create superheroes and sidekicks in
different threads. It contains a list of possible superheroes and sidekicks pairs,
from which one is selected at random. Whether that person is a superhero or sidekick
is chosen randomly, they are constructed with that information, and enter() is called
with them.

methods:
run(): Whether the person will be a superhero or sidekick is decided by randomly
generating a boolean. If it is true, a superhero is created, otherwise a sidekick
is. In either case, enter is called on the person.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

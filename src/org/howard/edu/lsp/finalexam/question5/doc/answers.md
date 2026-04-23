# Final Exam Question 5 Answers

Heuristic 1:
Name:
H3.2 – Do not create god classes/objects in your system. Be very suspicious of a class whose name contains Driver, Manager, System, or Subsystem.
 
Explanation:
A god class is one that ends up centralizing too much of the system's logic or data, making it a bottleneck for both understanding and maintenance. We talked about this in class using a home heating system as the example. The bad version had a HeatFlowRegulator that was doing everything — it was reaching into Room to grab the current temperature, the desired temperature, occupancy status, doing all the computation itself, and then telling the Furnace what to do. The problem is that HeatFlowRegulator now knows way too much about the internals of Room, so any change to Room can break it. The better version just lets Room decide whether it needs heat and exposes that as a single method. Now HeatFlowRegulator just asks "do you need heat?" and acts on the answer. It's much easier to read and change either class without breaking the other.
 
---
 
Heuristic 2:
Name:
H5.1 – Inheritance should be used only to model a specialization hierarchy (i.e., an "is a special kind of" relationship, not an "is a role played by a" relationship).
 
Explanation:
Inheritance breaks down when you use it to model roles instead of true specializations, because objects may need to change roles over time and there's no clean way to handle that with a static class hierarchy. We went through this in class with a person, passenger, and agent example. The first attempt made Passenger and Agent subclasses of Person, which sounds reasonable at first, but the problem is that the same person could be a passenger on one flight and work as an agent on another — so the object would need to change its type, which doesn't work. The fix was to pull roles out into their own hierarchy, so Passenger and Agent inherit from a PersonRole class, and Person just holds a reference to whatever role it's currently playing. This keeps the design flexible and avoids having to hack around type-switching at runtime, which makes things much easier to maintain.
 
---
 
Heuristic 3:
Name:
H3.3 – Beware of classes that have many accessor methods defined in their public interface. Having many access methods implies that related data and behavior are not being kept in one place.
 
Explanation:
When a class has a lot of getters and setters, it's usually a sign that some other class is pulling out data to do work that the first class should be doing itself. This came up in class as part of the heating system discussion. An earlier version of the design had the regulator calling several different methods on Room just to gather enough information to make a decision. The point made in lecture was that you should ask yourself: "why am I pulling all this data out of another class — why doesn't that class just do it for me?" Getters and setters aren't inherently wrong, but a pile of them is a red flag that responsibilities are in the wrong place. Once you move the logic where the data lives, both classes get simpler and you're less likely to break things when something changes.
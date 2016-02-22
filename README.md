The Thought Engine

Deriving Inference Meaning by Meme Sharing

Overview/Whitepaper

Ian Lawson, SSA/TechLead, Red Hat WEMEA



Overview/Precis

This whitepaper will detail the mechanisms by which the intended Artificial Intelligence implementation 'Thought Engine' will both store memories and infer meaning/share meaning algorithms with other instances of itself.

The idea behind the Thought Engine was to produce a thinking machine that wasn't bound by the basic rules/heuristic approach common to most AI implementations. This implementation treats both the persistence of information (memories) and the way in which additional information can be inferred and ranked as to correctness (memes) as mutable constructs (with the ability to harden the persistence of memories based on 'rightness'), and as mutable information allows the data and code itself to change based on the weighted inputs of provided information.

The basic concept is as follows – each piece of information persisted by a Thought Engine will have a 'confidence' factor, which will equate to a percentage. This can be used directly with no alteration to decide whether the information will be persisted appropriately, whether it will contribute to inferences, whether the source of information is trustworthy and the weight that this information brings to an inference.

The concept of inference is where the Memes are used. The Memes are atomic pieces of decision code that can be used to provide the 'confidence' of information, and also can be used to generate/infer other pieces of information (or changes of the original information). These Memes will be used as swappable currency within instances of the Thought Engine, and will themselves have a 'confidence' rating that will be the relevance/confidence to the Thought Engine storing the Meme (i.e. the confidence will not be passed around as part of the Meme but instead calculated by the receiving Thought Engine. In fact the Thought Engine itself will be able to use other Memes to score the confidence of the Memes.

The Thought Engine will be able to construct complex solutions for rating confidence an inferring information based on the combination of Memes. In some cases the combination of Memes will provide incompatible solutions (i.e. 'colour is only black' and 'colour is only white' are example statements that are 'contradictions') but these will generate a poor 'confidence'. The Thought Engines themselves will maintain a throttled mechanism for internal clean-up of paradoxes and contradictions, and this mechanism will be itself influenceable by Memes.

The main goals of this implementation are to potentially simplify the working mechanism of an AI so that:

1: The concept of memory can be easily quantified into a currency that can be shared and persisted.

2: The concept of non-rule based learning can be easily quantified into a currency that can be shared and persisted.

3: The Thought Engines will have the ability to produce and mutate any of the mechanisms that directly effect the production and persistence of the currencies in such a way that they can derive not only new meanings but new ways of determining meaning without external (human) input.

The key factor here is the currencies. If these can be successfully implemented in a way that makes them shareable and create-able then the Thought Engines can be very streamlined engines with different levels of persisted information reflecting the surety of the data.

In essence this will allow for modelling of the following concepts:

1: Awareness through differing levels of resisted memories – memories with stronger levels of confidence will have a higher chance of not being culled and will contribute more regularly to new inferences.

2: Creation of better inferences through mutation – the Memes themselves will be mutable, allowing a configurable level of mutation based on the confidence of the data. This will be implemented by having fixed Meme code, and combining the code in different ways rather than changing the atomic Meme code itself (changing the code would generate too much noise in terms of unusable Memes and wouldn't reflect the way in which the human brain builds and reinforces new conceptual ideas).

3: Implementation of a realistic reinforcing model for memories. The use of confidence ranked Memes to reinforce the confidence of data should allow for a more natural way of deciding which pieces of data are more important. In essence the Thought Engines will have a confidence for an individual atomic piece of information based on it's repeated usage, the confidence of the source (i.e. defaulting to 100% for self generated, using a perception model for information provided by external entities which will allow the Thought Engine to model trust and its effects on data absorption).

The Thought Engine will not model natural languages, but rather have a base taxonomy and syntax that will be expressive enough for complicated constructs but simple enough for ease of use. This currency will be described fully later on in the paper.

The Thought Engine will have two distinct components within itself, the Memory, made up of a confidence ranked set of components adhering to the currency to be defined in the paper, and the Inference Engine, made up of pre-defined (but mutable) rules and heuristics and a set of confidence ranked Memes. The Memes will adhere to a defined interface, which will be defined in detail later in the paper, and this interface will be the mechanism by which the Inference Engine scores and generates new currency components.

The Thought Engine will also implement two additional interfaces, one allowing for human interaction through a strictly defined set of operations (add memory, add meme, perform cleanup, perform inference, describe) and the other for machine to machine interaction, offering the same interface as with the human interface. In essence there will be one internal interface for interacting with the Thought Engine and two entry points, one for human interaction, the other for machine. The currency for information exchange will be identical under the covers, and one of the tests at a later point will be for a Thought Engine to see if it can recognise the patterns of behaviour of another Thought Engine versus a human actor using statistical and heuristic analysis of the messages provided. What would be nice is if the Thought Engine can be taught to recognise another entity, which would be an additional proof point for effective Artificial Intelligence.

Currency of Memories

The concept here is to provide a defined currency by which Memories can be appropriately stored and which allows a richness of information without becoming overly complex for interpretation.

All currency components will have additional metadata associated with them to allow the Thought Engine to correctly use and quantify the information. This will initially be 'confidence', indicating a percentage value showing the level of surety in this piece of information currently held by the Thought Engine, 'age', indicating the number of 'cycles' the information has been in existence and 'source', a label indicating where the data originated from. The concept of 'cycles' will be identical across all instances of the Thought Engine and reflect the number of iterations the Thought Engine has executed (rather than time based – this will allows for a more normalised indication of effort that will be relevant across all Thought Engines. The concept of 'source' will be a non-deterministic label which indicates nothing other than the a named source (it will be the job of the Thought Engine itself to derive additional information, through 'instances', as to the source of the information.

The definition tries to model the conceptual memory of a human being, and does it using the following different types of information:

The Instance

An instance is a singular piece of information based on a name-value pair. The Thought Engine will not implement the concepts of lists of information, but rather and more simply store multiple name-value pairs with the same name, i.e. the name will not be unique within the memory. 

The Instance acts as an atomic piece of memory, and instances themselves can be associated with higher level currencies (defined in this section) to imply ownership, but can also be non-associated. 

It will be the job of Memes to generate additional memories that associate instances, and other instances. 

Important – the Instance will also have within its data the ability to state a 'not' state. This will allow Thought Engines to maintain one form of Instance and not have external logic (although external logic will be implemented in Memes for association and inference). In action this means that the Thought Engine will have instances such as - “colour:black” “colour:white” “colour:NOT blue”. This inclusion of NOT logic in the value itself allows for a much more simplistic and easy to interpret data set without extraneous 'extra-instance' logic. The goal is to make the memories simple to store and interpret without having to maintain extra information alongside the memories.

As previous stated the instances can be associated to higher level currency elements but the inference is never stored in the instance itself. It is the job of the higher level currency components to maintain the association and keep the instances agnostic.

The Type

The Type is a currency component designed to allow the Thought Engine to group together information to provide a definition of type within its memory. This Type consists of the usual metadata (as defined in The Instance) along with a set of associated instances. The Type itself is singular, i.e. a grouping of instances, but can inherit information from Archetypes (defined below). It will be the job of the Thought Engine to infer types, when not provided, and to recognise Types by grouping of information.

Types can be seen as the pragmatic prototyping of objects within the Thought Engine. Objects are defined later in the section, but put simply an object will have a type that defines and provides pre-defined instances. But in addition an Object will be non-associated Instances that can contradict the instances defined by its assigned Type, and a Thought Engine should be able, in time, to determine the validity of non-associated instances against a defined type and produce more relevant types for objects.

Types are fully defined sets of instances – this will make more sense after the definition of Archetypes.

An example type would be “dog: [colour:black, colour:white, legs:4, tail:long]” where the instances define the acceptable values for instances that would make a 'dog'.

The Archetype

The archetype can be seen as a guiding template for Types in that it:

1: Contains no fixed instance data

2: Contains a list of instance names along with an indication of whether that instance is mandatory or not

In essence the archetype lists a set of instance names that have to be present for the Object to conform to the archetype definition. It does not contain limiting instance values that directly define the Object, that is the job of the Type.

A Type can have multiple Archetypes. There may be the ability to inherit archetypes, that has yet to designed within the Thought Engine. In that case the Thought Engine will be able to apply multiple levels of abstraction through inheritance and define a logical definition for Types that would be very powerful. 

This can be seen as having a Type that would 'implement' the required Instances defined by one of more Archetypes. The Type can be seen as a more physical indication of completeness whereas an archetype can be seen as a guideline for production of Type.

The key message around Archetypes is that they define rather than state the nature of a type, and that there is no final information in an Archetype.

The Object

An Object is a 'physical' instantiation within the Thought Engine. It consists of a name, which defines 'it', a set of non-associated Instances, zero or one Types and the appropriately associated instances and zero or more Archetypes which define the Instances required (without values). 

An Object does not need to be completely defined. For instance, an Object can have an associated Archetype but not have the defined Instances within itself. This is defined as a 'non-realised' Archetype but allows the Thought Engine the ability to infer and realise an Archetype.

The definition of an Object is also non-fixed in that a Thought Engine can have an Object with no components within it. Such an object should be quickly rationalised out but this is one of the aspects that the system is designed to do itself as opposed to being forced in a non-intuitive, non-intelligent way to discard data.


Persistence and Storage of Memories

The definition of currency provides a complex and useful set of tools for simulating and implementing a human-like set of concepts, but the most important aspect of them is that the implementation of a simple level of currency allows for additional clever complexities to be implemented in the way in which they are stored.

The Thought Engines will have the concept of a Memory Cell, which in simple terms will be a box in which currency can be stored, These cells will not be singular, i.e. a Thought Engine will have the ability to have multiple cells and, most importantly, each Cell is separate and unique. This will allow a singular Thought Engine to have effectively contradictory memories without complicating the storage of currency – the Thought Engine will be able to compartment information.

Each Cell will consist of a pool for non-associated Instances, a pool for Types, a pool for Archetypes and a pool for Objects. Within the Cell an appropriate level of implied uniqueness will be maintained (i.e. depending on the nature of the Thought Engine, there may be a rule which defines that all archetypes must be unique, and in this case that rule will be applied within the Cell, meaning that within the Cell itself the Archetype name must be unique, but a Thought Engine can have multiple Cells, and each Cell can have one copy of the named Archetype.

This allows for a powerful level of storage for a Thought Engine to be able to do cross-Cell comparative analytics and inference.

At a higher level again will be the concept of long-term and short-term memories. These will consist of two distinct storage points for Cells, with Cells in the short-term memories having an effective expiry date (based on the Cycles) which defines the point as which a Cell's contents have to have either been promoted to the long term memory storage or discarded. This expiry date will initially be associated with an entire Cell, but depending on results and behaviour may be extended into the currencies themselves (i.e. a Cell would have a defined life-span but rather than emptying the entire contents of the Cell each component would be assigned a start point and have that duration to become confident enough to be promoted to long term memory or discarded – this is a far better way of modelling memory in humans).
Manipulation of Memory – Memes and Assertions

Now we have a defined set of Currency and a defined mechanism for storage we will talk about how the data is manipulated and provisioned.

At the highest level there are simply two mechanisms for adding to the data stored in a Thought Engine:

1: Manual Assertion – this is the mechanism by which an external entity submits information to a Thought Engine. The way in which a Thought Engine absorbs the information is not fixed – the most basic implementation would be to simply store the data provided in an appropriate short term memory cell and tag it with appropriate metadata, i.e. an initial confidence rating for all aspects of the information based on pre-stored knowledge about the source or, in the case of a new source, an initial confidence based on the default confidence for the Thought Engine. This is an interesting angle as the default confidence for a Thought Engine could be determined by the normalisation of all previous confidences (i.e. a Thought Engine could become untrusting by being repeatedly given false or paradoxical information, or a Thought Engine could be naturally naïve by always associating a high confidence with information from a hitherto unseen source. Manual Assertion would involve no initial pre-processing of the provided information by the Thought Engine, but instead simply storing it for later perusal in an appropriate expiry-dated Cell.

2: Inferred Assertion – in this case information will be created or inferred by the Thought Engine itself based on a set of pre-provided Memes and/or a set of manually asserted Memes and/or a set of inferred Memes. Simply put, this action of information insertion would be driven from within the Thought Engine and the data provided would be created in some way by the Thought Engine.

The second mechanism is where the Thought Engine will start to shine. The creation of information and the assertion of information will use the same interfaces, in the case of manual assertion the information comes from outside the Thought Engine, in the case of inferred it is generated by the Thought Engine.

In addition to Assertion the Thought Engine will also be able to manipulate its own information as part of a 'thinking' process.

This is a little harder to define but the basics are as follows:

1: Periodically, depending on the 'mood' of the Thought Engine and/or driven by events generated by Memes, the Thought Engine will enter a 'sleep' cycle. During this cycle it will randomly, based on Memes, choose information to examine, where this level of randomness could be driven by the confidence of the information, paradoxes, the age of information (both new and old, with new it would be looking at short term formed information, with old it would examining information that had reached its expiry point). The Thought Engine will use Memes to examine the information in question and, as a result, it could change the confidence, create new information, remove information, infer information etc. 

2: Memes will be defined as such – a Meme will take a given set of inputs, perform a transformation and return a set of outputs and, most importantly, a threshold for activation. The threshold for activation will be a percentage figure that indicates a probability of activation, and the Thought Engine will effectively roll a weighted die and act upon the output. This way nothing is fixed as being certain (unless the confidence/probability works out as 100% of course).


Mutation and Genetic Algorithms

The nature of the memories and Memes within the Thought Engine may lend themselves to the use of the standard Genetic Algorithm approach as part of the sleep cycle/inference behaviour. 

This would involve, as a separate operation from rationalisation and assertion, the alteration of data within the Cells by random and mutation mechanisms.

This is both a powerful and potentially destructive way of changing the state of the Cells within an Thought Engine, and its use would need to be carefully considered and implemented.

One implementation in design is as follows:

1: Select a unit of Currency within the Cell.

2: Depending on the Unit of Currency apply a thresholded mutation – i.e. in the case of a value within an instance take the physical contents of the value and apply a point mutation, where the position and length of the mutation is decided randomly based on the size of the data, and the relevant chunk of data is altered randomly based on a genetic approach, i.e. either do a flip ('abcdef', start point 2, length of genome 2, results in 'acbdef') or do a randomised replace ('abcdef', start point 3, length 2, randomised results in something like 'abfaef', where it has chosen to replace the individual components with a randomised choice from the others).

3: Depending on a randomised and thresholded check, either replace the original data with the mutated data or add an additional identically named component (where you can – obviously with a unique Archetype approach you cannot have multiple same-name Archetypes). This choice would again depend on the genetic definitions (i.e. in some states you must retain the length of genome, for instance it must have 8 characters, and in some the length of the genome is inconsequential).

4: For realism you must then set the confidence of the new data to be much less than the original. Again, this would be configurable depending on the behaviour required – with a chaotic system the confidence would be as high as possible (in an entirely chaotic system you could use 100% but this would skew the information massively like an intellectual virus).

This approach will be implemented later once the underlying mechanisms are proven as it can introduce a wild amount of information change.

Implementing Memes

The unique feature of this implementation is the ability to consume mutable code for inference and data assertion. In essence the systems will be able to tailor and produce code to perform the data updates and culling without human interactions.

How this will work is as follows:

1: There will be a strictly defined interface for the implementation of a Meme. This will provide the Thought Engines with a callable interface to the Meme that is identical to all other Memes, meaning that the implementation code for a Meme can be changed without having to change the Thought Engines.

2: Each Meme will have a set of required inputs and provide a set of outputs along with an activation probability. Put simply, no Meme will ever alter data directly but instead act as a processor that takes input, performs a translation based on the inputs alone, and provides output that the Thought Engine can then decide itself whether to absorb or discard.

3: Each Meme will act as a component of the Cell. Put simply each Cell will have it own set of behaviours, and these behaviours will be ranked depending on a number of factors including the trust level of the Thought Engine, the resultant confidence changes provided by the output of the Meme (i.e. if the resultant data provides less confidence then the Meme will be ranked as less useful) and the trust level of the source (i.e. if the source of the Meme, be it a human or another Thought Engine, has a track record, by confidence, of bad information, then the Meme will be classed as less trustworthy.

Networking Thought Engines

The eventual goal is to be able to fire up Thought Engines with their own stored memories (Cells) and allow them free reign to talk to each other.

This would use the previously defined interfaces but importantly would take the following aspects into consideration:

1: All Thought Engines that talked to each other would maintain confidence levels for the information provided by each of the entities they talk to. They would also have the capacity to share confidence information and therefore all Thought Engines in a Cluster would need to be uniquely identifiable and consistently identifiable.

2: All Thought Engines in a Cluster would share a normalised perception of time Cycles. As stated earlier this would be normalised to a set of operation ticks as opposed to using real-world timings, as synchronisation will be far easier (and the use of Cycles for triggering sleep, thoughts etc consistent) across all members of a Cluster.

3: All persisted and stored memory Cells would be uniquely associated with a named Thought Engine. As the state of the Thought Engine would be entirely defined by the Cells, this allows the compute mechanisms for the Thought Engines to be transient, i.e. a Thought Engine can be torn down from one compute source and recreated elsewhere, the Thought Engine without the Cells is simply a dependency-injected 'sausage machine'. This also allows the use of a single Thought Engine to simulate many Thought Engines buy clearing and loading the appropriate Cells into the compute. This allows for nice little self-contained simulations of Clusters without the overhead of creating massive simultaneously active Thought Engines (all of the behaviours and interactions are normalised using the 'Cycles'.

4: In an active Cluster model the Thought Engines can (and should) be tied into a persisting message system, such that they all subscribe to a persisted message queue. This would allow for Thought Engines to be fired up and fired down whenever, and the temporal processing of messages to be handled and guaranteed by the messaging system. In effect this would simply require an implementation of the defined interaction interfaces to be done using message queues as the source of all input. The Thought Engines themselves should end up being self-healing and eventual-state using this process but for decent results the recommendation would be active Cluster components running simultaneously (I foresee some race-cases that would have a detrimental effect on confidences).

Conclusions

Using the simplistic bottom-up (currency->Cells->assertion/inference) it should be relatively simple to produce a prototype quickly to show the base behaviours. The use of Memes-as-Code is a very powerful and flexible way to implement behaviours, generated by the Thought Engines themselves, and even using Memes to score/rank Memes. 

The initial coding of currency has been completed and once the initial Thought Engine implementation has been finalised the entire project will be Open Sourced at github (https://github.com/utherp0).



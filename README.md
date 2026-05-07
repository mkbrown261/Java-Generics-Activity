# Java Generics Activity

A complete Java Generics lab assignment covering Generics fundamentals through five structured steps: Read, Write, Fix, and Create.

## Files Overview

| File | Step | Description |
|------|------|-------------|
| `Holder.java` | Read | Generic `Holder<T>` class — annotated with line-by-line comments and expected output |
| `Read.java` | Read | Five annotated code examples covering generic methods, Pair class, bounded types, autoboxing, and wildcards |
| `Write.java` | Write | Five implemented solutions: Generic Stack, findMax, swap, bounded sum, Pair with swap |
| `Fix.java` | Fix | Six buggy programs — each bug identified, explained, and corrected |
| `Create.java` | Create | Five original designs: Repository, Triple, Calculator, Queue, Result wrapper |

---

## Key Concepts Demonstrated

### Generics Glossary

| Term | Definition |
|------|-----------|
| **Generics** | Feature allowing classes/methods to operate on type parameters, enabling type-safe reuse |
| **Type Parameters** | Placeholders like `<T>`, `<E>`, `<K,V>` representing an unknown type supplied at compile time |
| **Boxing** | Manual conversion of a primitive (e.g. `int`) into its wrapper object (`Integer`) |
| **Unboxing** | Manual/automatic extraction of a primitive value from its wrapper object |
| **Autoboxing** | Automatic boxing performed by the Java compiler (`Integer i = 5;`) |
| **Wrapper** | Classes (`Integer`, `Double`, `Boolean`, etc.) that wrap primitives for use with generics |
| **Diamond Operator** | `<>` syntax that lets the compiler infer the generic type (`new ArrayList<>()`) |

---

## Step-by-Step Breakdown

### Step 2 — Read (`Holder.java`, `Read.java`)
Each line of code is commented explaining:
- What the line does
- Where autoboxing/unboxing occurs
- Where the diamond operator is used
- Expected output for each problem

**Holder.java Output:**
```
Holding: Hello
Holding: 42
Holding: 3.14
Extracted value: Hello
Updated: Holding: 100
```

**Read.java Output:**
```
=== Problem 1: Generic Method ===
Java Generics
100
99.99
true

=== Problem 2: Generic Pair Class ===
Key: name, Value: Alice
Key: age, Value: 30

=== Problem 3: Bounded Type Parameter ===
Sum = 15.0
Sum = 7.5

=== Problem 4: Autoboxing & Unboxing ===
Stored integers: [10, 20, 30, 40, 50]
Total: 150
Average: 30.0

=== Problem 5: Wildcard ===
[Hello, World, Java]
[1, 2, 3]
[1.1, 2.2, 3.3]
```

---

### Step 3 — Write (`Write.java`)

| Problem | Solution | Concepts Used |
|---------|----------|--------------|
| 1 | `Stack<T>` — push, pop, peek, isEmpty, size | Generic class, ArrayList, autoboxing |
| 2 | `findMax(T[])` — find largest element | `<T extends Comparable<T>>`, bounded type |
| 3 | `swap(T[], i, j)` — swap two array elements | Generic method, temp variable |
| 4 | `sumNumbers(List<? extends Number>)` | Upper-bounded wildcard, unboxing |
| 5 | `Pair<A,B>` with `swap()` → `Pair<B,A>` | Two type params, reversed generic return type |

---

### Step 4 — Fix (`Fix.java`)

| Problem | Bug Description | Fix Applied |
|---------|----------------|-------------|
| 1 | `Box` missing `<T>` — used `Object` instead of `T` | Added `<T>` to class; replaced `Object` with `T` |
| 2 | `printAll(List<Object>)` — doesn't accept `List<String>` etc. | Changed to `List<?>` (unbounded wildcard) |
| 3 | `<T>` unconstrained — `doubleValue()` not found | Changed to `<T extends Number>` |
| 4 | `pop()`/`peek()` used index `0` (FIFO) instead of last (LIFO) | Changed to `size()-1` for correct stack behavior |
| 5 | `null` in `List<Integer>` caused `NullPointerException` on unboxing | Added null check before `total += s` |
| 6 | Raw type `new ArrayList()` — bypasses type checking | Replaced with `new ArrayList<>()` (diamond operator) |

---

### Step 5 — Create (`Create.java`)

| Problem | Design | Highlights |
|---------|--------|-----------|
| 1 | `Repository<T>` | In-memory CRUD store with auto-incrementing IDs using `HashMap<Integer,T>` |
| 2 | `Triple<A,B,C>` | Three-element tuple with `reverse()` returning `Triple<C,B,A>` |
| 3 | `Calculator<T extends Number>` | sum, average, max, min using `doubleValue()` unboxing |
| 4 | `Queue<T>` | FIFO queue with enqueue, dequeue, peek — contrast to Stack LIFO |
| 5 | `Result<T>` | Success/Failure wrapper with factory methods; wildcard `printResult(Result<?>)` |

---

## How to Compile and Run

```bash
# Compile all files
javac Holder.java Read.java Write.java Fix.java Create.java

# Run individual files
java Holder
java Read
java Write
java Fix
java Create
```

**Requirements:** Java 8+ (uses generics, diamond operator, enhanced for loops)

---

## Author
mkbrown261

package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
//        testConsumer(s -> {
//            System.out.println(s);
//            System.out.println("2nd line");
//        }
//        testSupplier(() -> "print with Supplier");

//        testPredicate(p -> p.getAge() > 18);
//        testPredicate(p -> p.getAge() > 20);

        testFunction(name -> new Person(1,20,name));
        testFunction(name -> new Person(2,name.length(),name));
//
        testConsumer(System.out::println);
//        testConsume2r(Person::getAge);
//        testOptional(null);
//        testOptional(new Person(1,18,"Nazim"));
//        testOptional2(new Person(1,5,"Nazim"));
        System.out.println(generatePeople(Arrays.asList(
                new Person(1,1,"a"),
                new Person(2,30,"p2"))));
    }

    private static List<Integer> generatePeople(List<Person> people) {
        return people.stream().filter(p -> p.getAge()>5).map(Person::getId).collect(Collectors.toList());
    }

    public static void testFunction(Function<String, Person> function){
        Person p = function.apply("Asiman");
        System.out.println(p);
    }

    public static void testSupplier(Supplier<String> supplier){
        System.out.println(supplier.get());
    }

    public static void testPredicate(Predicate<Person> predicate){
        boolean res = predicate.or(p -> p.getName().equals("Memmed")).test(new Person(1,22,"Memmed1"));
        System.out.println(res);
    }

    public static void testConsumer2(Consumer<Person> consumer){
        consumer.accept(new Person(1,40,"Nazim"));
        System.out.println(consumer);
    }

    public static void testConsumer(Consumer<String> consumer){
        consumer
                .andThen(p -> System.out.println(p + p))
                .andThen(a -> System.out.println(a.toUpperCase(Locale.ROOT)))
                .andThen(generateConsumer())
                .accept("Asiman");
    }

    public static Consumer<String> generateConsumer(){
        return p -> System.out.println(p.substring(3));
    }

    public static void testOptional2(Person p){
        System.out.println(Optional.ofNullable(p)
                .filter(item -> item.getAge() > 18)
//                .orElse(new Person(100, 0,"Default"))
                .orElseThrow(RuntimeException::new));
    }

    public static void testOptional(Person p){
        Optional.ofNullable(p).
                filter(item -> item.getAge() > 18)
                .map(Person::getName)
                .filter(s->s.startsWith("N"))
                .ifPresent(System.out::println);
    }

    public static void generatePeople(){
         IntStream.range(1,100)
                .boxed()
                .map(i -> new Person(i,i+1,"name" + i))
                .filter(p -> p.getId() > 25)
                .limit(10)
                .forEach(System.out::println);
//                .collect(Collectors.toList());
    }
}
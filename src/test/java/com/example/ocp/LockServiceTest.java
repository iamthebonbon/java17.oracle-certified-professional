package com.example.ocp;

import com.example.ocp.locks.LockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockServiceTest {
    private static final Logger LOGGER = Logger.getLogger(LockServiceTest.class.getName());

    private final LockService lockService = new LockService();

    @Test
    public void test() {
        Balance one = new Balance(UUID.randomUUID().toString(), 100L);
        Balance two = new Balance(UUID.randomUUID().toString(), 100L);

        AtomicBoolean flag = new AtomicBoolean();
        IntStream.range(0, 10)
                .parallel()
                .forEach(x -> {
                    Object lock = lockService.getLock(calculateLockKey(two.getId(), one.getId()));
                    synchronized (lock) {
                        if (flag.getAndSet(!flag.get())) {
                            two.setBalance(two.getBalance() + 10);
                            one.setBalance(one.getBalance() - 10);
                        } else {
                            one.setBalance(one.getBalance() + 10);
                            two.setBalance(two.getBalance() - 10);
                        }
                    }
                });

        Assertions.assertEquals(1, lockService.getLocks().size());
        Assertions.assertEquals(100, one.getBalance());
        Assertions.assertEquals(100, two.getBalance());
    }

    public String calculateLockKey(String... args) {
        return Arrays.stream(args)
                .sorted(String::compareTo)
                .collect(Collectors.joining(""));
    }

    public static class Balance {
        private String id;
        private Long balance;

        public Balance(String id, Long balance) {
            this.id = id;
            this.balance = balance;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Long getBalance() {
            return balance;
        }

        public void setBalance(Long balance) {
            this.balance = balance;
        }
    }

}

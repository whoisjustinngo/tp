package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;



class PolicyTest {

    @Test
    void equals() {
        Policy policy = new Policy("AIA", "1", "Critical Illness Policy", "2.0");

        // same Policy ID
        Policy policySameCommission = new Policy("AIA", "1", "Critical Illness Policy", "2");
        assertEquals(policy, policySameCommission);

        // different insurer
        Policy policyDiffInsurer = new Policy("AIG", "1", "Critical Illness Policy", "2.0");
        assertNotEquals(policy, policyDiffInsurer);

        // different number
        Policy policyDiffNumber = new Policy("AIA", "2", "Critical Illness Policy", "2.0");
        assertNotEquals(policy, policyDiffNumber);

        // different name, but same id
        Policy policyDiffName = new Policy("AIA", "1", "Critical Illness Policy v1", "2.0");
        assertEquals(policy, policyDiffName);

    }

}

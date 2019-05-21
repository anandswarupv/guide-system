package org.metrotransit.guide;

import org.junit.Test;

public class GuideApplicationTest {

    @Test
    public void shouldReturnHowLongUntilNextBus() {
        String[] args = {"Express - Target - Hwy 252 and 73rd Av P&R - Mpls", "Target North Campus Building F", "south"};
        GuideApplication.main(args);
    }

    @Test
    public void testForLightRail() {
        String[] args = {"METRO Blue Line", "Target Field Station Platform 1", "south"};
        GuideApplication.main(args);
    }

}
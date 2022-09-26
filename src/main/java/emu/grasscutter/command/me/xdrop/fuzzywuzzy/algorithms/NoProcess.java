package emu.grasscutter.command.me.xdrop.fuzzywuzzy.algorithms;

import emu.grasscutter.command.me.xdrop.fuzzywuzzy.StringProcessor;

/**
 * @deprecated Use {@code ToStringFunction#NO_PROCESS} instead.
 */
@Deprecated
public class NoProcess extends StringProcessor {

    @Override
    @Deprecated
    public String process(String in) {
        return in;
    }

}

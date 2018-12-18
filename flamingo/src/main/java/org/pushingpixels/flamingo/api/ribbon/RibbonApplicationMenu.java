/*
 * Copyright (c) 2005-2018 Flamingo Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Flamingo Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.flamingo.api.ribbon;

import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.model.*;
import org.pushingpixels.flamingo.api.common.popup.model.CommandPopupMenuContentModel;
import org.pushingpixels.flamingo.api.ribbon.projection.RibbonApplicationMenuCommandProjection;

import java.util.*;

/**
 * Metadata description of the application menu of the {@link JRibbon} component. The ribbon
 * application menu has three parts:
 *
 * <pre>
 * +-------------------------------------+
 * |           |                         |
 * |           |                         |
 * |  primary  |        secondary        |
 * |   area    |           area          |
 * |           |                         |
 * |           |                         |
 * |-------------------------------------|
 * |            footer area              |
 * +-------------------------------------+
 * </pre>
 *
 * <p>
 * The entries in the primary area are always visible. The secondary area entries are shown based on
 * the currently active element in the primary area. The secondary entries are {@link Command}s
 * configured with the
 * {@link Command.Builder#setPopupMenuContentModel(CommandPopupMenuContentModel)}
 * API. When this entry is armed (with mouse rollover or via keyboard navigation), the secondary
 * area shows menu buttons for the registered secondary menu entries. The <code>Save As</code> menu
 * item is an example of such a primary menu item, showing a list of default save formats.</li>
 * </ul>
 *
 * <p>
 * At runtime, the application menu entries are implemented as {@link JCommandMenuButton}, but the
 * application code does not operate on that level. Instead, the application code creates
 * metadata-driven description of the ribbon application menu with {@link Command.Builder}, and
 * those commands is used to create and populate the "real" controls of the application menu popup.
 * </p>
 *
 * <p>
 * Note that once a {@link RibbonApplicationMenu} is set on the {@link JRibbon} with the
 * {@link JRibbon#setApplicationMenuCommand(RibbonApplicationMenuCommandProjection)},
 * its contents cannot be changed.
 * </p>
 *
 * @author Kirill Grouchnikov
 */
public class RibbonApplicationMenu extends CommandPopupMenuContentModel {
    /**
     * Footer commands.
     */
    private CommandGroup footerCommands;

    public RibbonApplicationMenu(CommandGroup commands) {
        super(commands);
        this.footerCommands = new CommandGroup();
    }

    public RibbonApplicationMenu(CommandGroup... commandGroups) {
        super(commandGroups);
        this.footerCommands = new CommandGroup();
    }

    public RibbonApplicationMenu(List<CommandGroup> commands) {
        super(commands);
        this.footerCommands = new CommandGroup();
    }

    /**
     * Adds the specified footer command.
     *
     * @param entry Footer command to add.
     * @see #getFooterCommands()
     */
    public synchronized void addFooterCommand(Command entry) {
        this.footerCommands.addCommand(entry);
    }

    /**
     * Returns the footer commands of this application menu. The result is
     * guaranteed to be non-<code>null</code>.
     *
     * @return The footer commands of this application menu.
     * @see #addFooterCommand(Command)
     */
    public CommandGroup getFooterCommands() {
        return this.footerCommands;
    }
}

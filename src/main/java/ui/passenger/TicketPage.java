package ui.passenger;

import controller.ticket.TicketViewModel;
import ui.UIController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class represents a UI page that displays multiple train tickets in a grid layout.
 * Each ticket is represented as a panel inside the main frame of the page.
 */
public class TicketPage {

    /** The UIController instance responsible for controlling UI interactions. */
    private final UIController controller;

    /** Main frame of the ticket page. */
    private final JFrame frame;

    /** Panel to represent a single ticket. */
    private JPanel panel;

    /**
     * Constructs a new TicketPage with the given UIController and list of ticket view models.
     *
     * @param controller the UIController instance responsible for controlling UI interactions
     * @param viewModels the list of ticket view models to be displayed
     */
    public TicketPage(UIController controller, List<TicketViewModel> viewModels) {
        this.controller = controller;

        frame = new JFrame("Train Tickets");
        frame.setLayout(new GridLayout(3, 4));
        frame.setPreferredSize(new Dimension(1100, 800));

        for (int i = 0; i < 3 * 4; i++) {

            if (i >= viewModels.size()) {
                frame.add(new JPanel());
                continue;
            }

            TicketViewModel viewModel = viewModels.get(i);
            panel = new TicketPanel(controller, viewModel);
            frame.add(panel);
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

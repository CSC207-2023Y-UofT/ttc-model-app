package ui.passenger;

import presenter.TransitMapPassengerPresenter;
import ui.UIController;
import ui.WelcomePage;
import ui.map.MapPanel;
import ui.round.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PassengerHomePage is a JPanel that displays the passenger home page.
 * It is used by the UIController to display the passenger home page.
 *
 * @see UIController
 */
public class PassengerHomePage extends JPanel {
    /**
     * The UIController that is used to control the UI.
     */
    private final UIController controller;
    /**
     * The buttons on the panel.
     */
    private JButton buyButton, backButton;
    /**
     * The map panel on the panel.
     */
    private MapPanel mapPanel;

    /**
     * Constructs a new PassengerHomePage with the given UIController.
     *
     * @param controller the UIController that is used to control the UI
     */
    public PassengerHomePage(UIController controller) {
        super(new BorderLayout(0, 1));

        this.controller = controller;

        // Buy
        buyButton = new RoundedButton("Buy Ticket");
        buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buyButton.setBackground(new Color(114, 217, 112));
        buyButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        buyButton.setFont(new Font("Serif", Font.BOLD, 20));
        buyButton.addActionListener(e -> controller.open(new PurchaseTicketPage(controller)));

        // Back button
        backButton = new RoundedButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBackground(new Color(255, 255, 255));
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.addActionListener(e -> controller.open(new WelcomePage(controller)));


        // Add components to the panel

        this.setBackground(new Color(210, 207, 206));

        // Map
        TransitMapPassengerPresenter presenter = new TransitMapPassengerPresenter(
                controller.getInteractorPool().getStationInteractor(),
                controller.getInteractorPool().getTrainInteractor()
        );
        mapPanel = new MapPanel(presenter);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(mapPanel);
        topPanel.add(new JLabel(""));

        this.add(topPanel);

        JPanel bottomPanel = new JPanel(new GridLayout(0, 3));

        bottomPanel.add(backButton, BorderLayout.WEST);
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(buyButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

}

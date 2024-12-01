
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CustomizedButton extends JButton {
    public CustomizedButton(String text, EnumsList.ButtonStyle style) {
        super(text.toUpperCase());

        // Apply different styles based on the ButtonStyle enum
        setStyle(style);

        // Set padding inside the button
        setMargin(new Insets(10, 20, 10, 20)); // Padding (top, left, bottom, right)

        // Set a fixed size for all buttons
        setPreferredSize(new Dimension(180, 40)); // Adjusted size for better appearance

        // Add mouse listener for hover effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change background color on hover
                setBackground(getHoverBackgroundColor(style));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Reset background color when hover ends
                setBackground(getBackgroundColor(style));
            }
        });
    }

    private void setStyle(EnumsList.ButtonStyle style) {
        Color initialColor = getBackgroundColor(style);
        setBackground(initialColor);

        switch (style) {
            case RED_BUTTON: // Minimalist Style
                setForeground(Color.WHITE);
                setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                setBorder(BorderFactory.createLineBorder(initialColor, 2)); // Rounded border
                setFocusPainted(false);
                break;
            case GREY_PURPLE_BUTTON: // Modern Gradient Style
                setForeground(Color.WHITE);
                setFont(new Font("Arial", Font.BOLD, 14));
                setBorder(BorderFactory.createLineBorder(initialColor, 2)); // Rounded border
                setFocusPainted(false);
                break;
            case LIGHT_PURPLE_BUTTON: // Rounded Style
                setForeground(Color.WHITE);
                setFont(new Font("Verdana", Font.BOLD, 16));
                setBorder(BorderFactory.createLineBorder(initialColor, 2)); // Rounded border
                setFocusPainted(false);
                setContentAreaFilled(true);
                setOpaque(true);
                break;
            case GREY_BUTTON: // Dark Mode Style
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 16));
                setBorder(BorderFactory.createLineBorder(initialColor, 2)); // Rounded border
                setFocusPainted(false);
                break;
        }
    }

    private Color getHoverBackgroundColor(EnumsList.ButtonStyle style) {
        switch (style) {
            case RED_BUTTON:
                return new Color(255, 102, 102); // Lighter Red for hover
            case GREY_PURPLE_BUTTON:
                return new Color(90, 120, 170); // Muted Blue for hover
            case LIGHT_PURPLE_BUTTON:
                return new Color(150, 75, 255); // Slightly darker purple
            case GREY_BUTTON:
                return new Color(160, 160, 160); // Lighter grey for hover
            default:
                return getBackground(); // Default background if no style is matched
        }
    }

    private Color getBackgroundColor(EnumsList.ButtonStyle style) {
        // Return the background color based on the style
        switch (style) {
            case RED_BUTTON:
                return new Color(255, 51, 51); // Red for Minimalist
            case GREY_PURPLE_BUTTON:
                return new Color(102, 102, 155); // Grayish Blue for Modern Gradient
            case LIGHT_PURPLE_BUTTON:
                return new Color(178, 102, 255); // Light Purple for Rounded
            case GREY_BUTTON:
                return new Color(128, 128, 128); // Dark Grey for Dark Mode
            default:
                return getBackground(); // Default background if no style is matched
        }
    }
}

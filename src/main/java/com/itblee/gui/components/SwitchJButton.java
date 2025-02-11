package com.itblee.gui.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SwitchJButton extends Component {
    private final Timer timer;
    private float location;
    private boolean selected;
    private boolean mouseOver;
    private final float speed = 0.3f;
    private final List<IEventSwitchSelected> events;

    public SwitchJButton(Color color) {
        setBackground(color);
        setPreferredSize(new Dimension(40, 20));
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        events = new ArrayList<>();
        location = 2;
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int endLocation;
                if (isSelected()) {
                    endLocation = getWidth() - getHeight() + 2;
                    if (location < endLocation) {
                        location += speed;
                    } else {
                        timer.stop();
                        location = endLocation;
                    }
                } else {
                    endLocation = 2;
                    if (location > endLocation) {
                        location -= speed;
                    } else {
                        timer.stop();
                        location = endLocation;
                    }
                }
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (mouseOver) {
                        selected = !selected;
                        timer.start();
                        runEvent();
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        float alpha = getAlpha();
        if (alpha < 1) {
            g2.setColor(Color.GRAY);
            g2.fillRoundRect(0, 0, width, height, 20, 20);
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 20, 20);
        g2.setColor(getForeground());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillOval((int) location, 2, height - 4, height - 4);
        super.paint(grphcs);
    }

    private float getAlpha() {
        float width = getWidth() - getHeight();
        float alpha = (location - 2) / width;
        if (alpha < 0) {
            alpha = 0;
        }
        if (alpha > 1) {
            alpha = 1;
        }
        return alpha;
    }

    private void runEvent() {
        for (IEventSwitchSelected event : events) {
            event.onSelected(selected);
        }
    }

    public void addEventSelected(IEventSwitchSelected event) {
        events.add(event);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        timer.start();
        runEvent();
    }
}

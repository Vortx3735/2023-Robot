// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * Add your docs here.
 */
public class VorTXController extends PS4Controller {
	public JoystickButton cross, circle, square, triangle, l2, r2, share, options, ls, rs, l1, r1;
	public PS4Controller c_driverController = new PS4Controller(3);
	public POVButton pov0, pov45, pov90, pov135, pov180, pov225, pov270, pov315;

	public VorTXController(int port) {
		super(port);
		
		
		square = new JoystickButton(this, 1);
		cross = new JoystickButton(this, 2);
		circle = new JoystickButton(this, 3);
		triangle = new JoystickButton(this, 4);
		l2 = new JoystickButton(this, 7);
		r2 = new JoystickButton(this, 8);
		share = new JoystickButton(this, 9);
		options = new JoystickButton(this, 10);
		ls = new JoystickButton(this,11);
		rs = new JoystickButton(this, 12);

		
		pov0 = new POVButton(this, 0);
		pov45 = new POVButton(this, 45);
		pov90 = new POVButton(this, 90);
		pov135 = new POVButton(this, 135);
		pov180 = new POVButton(this, 180);
		pov225 = new POVButton(this, 225);
		pov270 = new POVButton(this, 270);
		pov315 = new POVButton(this, 315);
	}

}
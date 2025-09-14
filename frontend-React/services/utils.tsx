import { listDisplayNames } from "./apis";

export const getDeviceImage = (deviceName: string): any => {
	const lower = deviceName.toLowerCase();

	if (lower.includes('light')) {
		return require('../assets/images/icons/light.png');
	} else if (lower.includes('air conditioner')) {
		return require('../assets/images/icons/air-conditioner.png');
	} else if (lower.includes('blind')) {
		return require('../assets/images/icons/blinds.png');
	} else if (lower.includes('sensor')) {
		return require('../assets/images/icons/sensor.png');
	} else if (lower.includes('oven')) {
		return require('../assets/images/icons/oven.png');
	} else if (lower.includes('radiator')) {
		return require('../assets/images/icons/radiator.png');
	} else if (lower.includes('solar')) {
		return require('../assets/images/icons/solar-panels.png');
	} else if (lower.includes('washingmachine')) {
		return require('../assets/images/icons/washing-machine.png');
	} else if (lower.includes('thermostat')) {
		return require('../assets/images/icons/thermostat.png');
	} else if (lower.includes('airconditioner')) {
		return require('../assets/images/icons/air-conditioner.png');
	} else if (lower.includes('dishwasher')) {
		return require('../assets/images/icons/dishwasher.png');
	} else {
		return require('../assets/images/icons/light.png');
	}
}

export const mapStatusString = (status: string): string => {
	if (status.startsWith('Thermostat status: temp=')) {
		const currentTemp = status.replace('Thermostat status: temp=', '').split(',')[0];
		return `${currentTemp}°C`;
	} else if (status === 'Blind status: is_up=false')
		return 'Down';
	else if (status === 'Blind status: is_up=true')
		return 'Up';
	else if (status.startsWith('Oven status: isOn=')) {
		const isOn = status.replace('Oven status: isOn=', '').split(',')[0];
		return `${isOn == 'true' ? 'On' : 'Off'}`;
	} else if (status.startsWith('Air conditioner status: is_on=')) {
		const isOn = status.replace('Air conditioner status: is_on=', '').split(',')[0];
		return `${isOn == 'true' ? 'On' : 'Off'}`;
	} else if (status.startsWith('WashingMachine status: program=')) {
		const isOn = status.replace('WashingMachine status: program=', '').split(',')[2].replace(' is_on=', '');
		const isRunning = status.replace('WashingMachine status: program=', '').split(',')[1].replace(' is_running=', '');
		return `${isOn === 'true' ? (isRunning === 'true' ? 'On\nRunning' : 'On\nNot Running') : 'Off'}`;
	} else if (status.startsWith('Light status: is_on=')) {
		const isOn = status.replace('Light status: is_on=', '').split(',')[0];
		return `${isOn == 'true' ? 'On' : 'Off'}`;
	} else if (status.startsWith('SolarPanel status: powerOutput=')) {
		const powerOutput = status.replace('SolarPanel status: powerOutput=', '').split(',')[0];
		const batteryStatus = status.replace('SolarPanel status: powerOutput=', '').split(',')[1].replace(' batteryStatus=', '');
		return `${powerOutput}W - ${batteryStatus}%`;
	} else if (status.startsWith('Dishwasher status: is_on=')) {
		const isOn = status.replace('Dishwasher status: is_on=', '').split(',')[0];
		const isRunning = status.replace('Dishwasher status: is_on=', '').split(',')[1].replace(' is_running=', '');
		return isOn === 'true' ? (isRunning === 'true' ? 'On\nRunning' : 'On\nNot Running') : 'Off';
	} else if (status.startsWith('MotionSensor status: is_on=')) {
		const isOn = status.replace('MotionSensor status: is_on=', '').split(',')[0];
		return `${isOn == 'true' ? 'On' : 'Off'}`;
	}
	return status;
}

export const mapStatusDevice = (deviceId: string, status: string): Array<string> => {
	let name = deviceId.toLowerCase();
	if (name.includes('blind')) {
		let statusTemp = status.replace('Blind status: is_up=', '');
		return ['blind', statusTemp === 'true' ? 'Up' : 'Down'];
	// MotionSensor status: is_on=false, motionDetected=false
	} else if (name.includes('motionsensor')) {
		let statusTemp = status.replace('MotionSensor status: is_on=', '');
		let motionDetected = status.replace('MotionSensor status: is_on=', '').split(',')[1].replace(' motionDetected=', '');
		return ['motionsensor', statusTemp === 'true' ? 'On' : 'Off', motionDetected === 'true' ? 'Detected' : 'Not Detected'];
	} else if (name.includes('thermostat')) {
		const currentTemp = status.replace('Thermostat status: temp=', '').split(',')[0];
		return ['thermostat', currentTemp];
	} else if (name.includes('oven')) {
		let isOn = status.replace('Oven status: isOn=', '').split(',')[0];
		let temp = status.replace('Oven status: isOn=', '').split(',')[1].replace(' temp=', '');
		let mode = status.replace('Oven status: isOn=', '').split(',')[2].replace(' mode=', '');
		let targetTemp = status.replace('Oven status: isOn=', '').split(',')[3].replace(' targetTemp=', '');
		return ['oven', isOn === 'true' ? 'On' : 'Off', temp, mode, targetTemp];
	} else if (name.includes('airconditioner')) {
		let isOn = status.replace('Air conditioner status: is_on=', '').split(',')[0];
		let mode = status.replace('Air conditioner status: is_on=', '').split(',')[1].replace(' current_program=', '');
		return ['airconditioner', isOn === 'true' ? 'On' : 'Off', mode];
	} else if (name.includes('dishwasher')) {
		let isOn = status.replace('Dishwasher status: is_on=', '').split(',')[0];
		let isRunning = status.replace('Dishwasher status: is_on=', '').split(',')[1].replace(' is_running=', '');
		let program = status.replace('Dishwasher status: is_on=', '').split(',')[2].replace(' program=', '');
		let remainingTime = status.replace('Dishwasher status: is_on=', '').split(',')[3].replace(' remainingTime=', '');
		return ['dishwasher', isOn === 'true' ? 'On' : 'Off', isRunning === 'true' ? 'Running' : 'Not Running', program, remainingTime];
	} else if (name.includes('washingmachine')) {
		let isOn = status.replace('WashingMachine status: program=', '').split(',')[2].replace(' is_on=', '');
		let isRunning = status.replace('WashingMachine status: program=', '').split(',')[1].replace(' is_running=', '');
		let program = status.replace('WashingMachine status: program=', '').split(',')[0];
		return ['washingmachine', isOn === 'true' ? 'On' : 'Off', isRunning === 'true' ? 'Running' : 'Not Running', program];
	} else if (name.includes('light')) {
		let statusTemp = status.replace('Light status: is_on=', '');
		return ['light', statusTemp === 'true' ? 'On' : 'Off'];
	}
	else if (name.includes('solarpanel')) {
		let powerOutput = status.replace('SolarPanel status: powerOutput=', '').split(',')[0];
		let batteryStatus = status.replace('SolarPanel status: powerOutput=', '').split(',')[1].replace(' batteryStatus=', '');
		return ['solarpanel', powerOutput, batteryStatus];
	}
	return ['unknown', status];
}


export function getStatusColor(deviceId: string, status: string | undefined): string {
	if (!status) return '#FFFFFF';

	const name = deviceId.toLowerCase();
	if (name.includes('motionsensor'))
		return status === 'On' ? '#00FF2F' : '#FF0000';
	else if (name.includes('blind'))
		return status === 'Up' ? '#00FF2F' : '#FF0000';
	else if (name.includes('oven'))
		return status === 'On' ? '#00FF2F' : '#FF0000';
	else if (name.includes('light'))
		return status === 'On' ? '#00FF2F' : '#FF0000';
	else if (name.includes('thermostat'))
		return '#00FF2F';
	else if (name.includes('solarpanel'))
		return '#00FF2F';
	else if (name.includes('dishwasher'))
		return status.startsWith('On') ? '#00FF2F' : '#FF0000';
	else if (name.includes('washingmachine'))
		return status.startsWith('On') ? '#00FF2F' : '#FF0000';
	else if (name.includes('airconditioner'))
		return status === 'On' ? '#00FF2F' : '#FF0000';
	return '#FFFFFF';
}

const ACModes = ['AIR_PROGRAM_UNSPECIFIED', 'AIR_PROGRAM_COOL', 'AIR_PROGRAM_HEAT'];
const ovenModes = ['OVEN_PROGRAM_UNSPECIFIED', 'OVEN_PROGRAM_STATIC', 'OVEN_PROGRAM_CONVECTION'];
const washingModes = ['DELICATE', 'QUICK_WASH'];
const dishModes = ['NORMAL', 'ECO', 'INTENSIVE', 'QUICK'];

export function mapPrograms(deviceId: string, program: string | undefined | null): string {
	if (!program) return 'Unknown';
	const name = deviceId.toLowerCase();
	if (name.includes('airconditioner')) {
		switch (program) {
			case ACModes[0]:
				return 'Unspecified';
			case ACModes[1]:
				return 'Cool';
			case ACModes[2]:
				return 'Heat';
			default:
				return program;
		}

	} else if (name.includes('oven')) {
		switch (program) {
			case ovenModes[0]:
				return 'Unspecified';
			case ovenModes[1]:
				return 'Static';
			case ovenModes[2]:
				return 'Convection';
			default:
				return program;
		}

	} else if (name.includes('washingmachine')) {
		switch (program) {
			case washingModes[0]:
				return 'Delicate';
			case washingModes[1]:
				return 'Quick Wash';
			default:
				return program;
		}

	} else if (name.includes('dishwasher')) {
		switch (program) {
			case dishModes[0]:
				return 'Normal';
			case dishModes[1]:
				return 'Eco';
			case dishModes[2]:
				return 'Intensive';
			case dishModes[3]:
				return 'Quick';
			default:
				return program;
		}

	}
	return 'Unknown';
}


export function getRoomImage(roomName: string): any {
	const lower = roomName.toLowerCase();

	if (lower.includes('bedroom')) {
		return require('../assets/images/icons/bedroom.png');
	} else if (lower.includes('kitchen')) {
		return require('../assets/images/icons/kitchen.png');
	} else if (lower.includes('office')) {
		return require('../assets/images/icons/office.png');
	} else if (lower.includes('living room')) {
		return require('../assets/images/icons/livingroom.png');
	} else {
		return require('../assets/images/icons/generic.png');
	}
};


export const getNames = async (): Promise<{}> => {
	const listName = await listDisplayNames();
	console.log('List Display Names:', listName);

	return {};
}

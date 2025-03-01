# Modbus Sungrow Binding

This binding integrates the sungrow inverters into openHAB.
It is based on the Sungrow specification "Communication Protocol of Residential Hybrid Inverter V1.1.15", which can be found here: https://github.com/Gnarfoz/Sungrow-Inverter/blob/main/Modbus%20Information/TI_20240924_Communication%20Protocol%20of%20Residential%20Hybrid%20Inverter-V1.1.5.pdf.

## Supported Inverters

As defined within the spec mentioned above the following inverters are supported, but not all are tested yet:

- SH3.0-6.0RS
- SH8.0-10RS
- SH5.0-10RT
- SH5-25T

Some values might not work for your inverter...

## Supported Things

The binding supports only one thing:

- `sungrow-inverter`: The sungrow inverter

## Preparation

The data from the inverter is read via Modbus. So you need to configure a Modbus Serial Slave `serial` or Modbus TCP Slave `tcp` as bridge first.
If you are using a Modbus TCP Slave and the WiNet-S Communication Module please ensure:

- that you have the correct IP address of your WiNet-S device
- that Modbus is enabled within the Communication Module
- that you have the correct port number
- that the white list is disabled or your openHAB instance IP is listed

Enabling modbus and whitelist setting can be done in WiNet-S Web-UI as shown below:
<img src="./doc/WiNet-S_Modbus.png" alt="WiNet-S Modbus configuration"/>

## Thing Configuration

Once you've configured the Modbus TCP Slave or Modbus Serial Slave as Bridge you can configure the Sungrow inverter thing.
You just have to select the configured bridge and optional configure the polling interval.

### Sungrow Inverter (`sungrow-inverter`)

| Name         | Type    | Description                          | Default | Required | Advanced |
|--------------|---------|--------------------------------------|---------|----------|----------|
| pollInterval | integer | Interval the device is polled in ms. | 10000   | yes      | no       |

## Channels

The `sungrow-inverter` thing has channels that serve the current state of the sungrow inverter, as you are used to from the iSolareCloud Website and App.

## Full Example

This example shows how to configure a sungrow inverter connected via modbus and uses the most common channels.

_sungrow.things_

```java
Bridge modbus:tcp:sungrowBridge [ host="10.0.0.2", port=502, id=1, enableDiscovery=false ] {
    Thing sungrow-inverter sungrowInverter "Sungrow Inverter" [ pollInterval=5000 ]
}
```

_sungrow.items_

```java
// Groups
Group sungrowInverter "Sungrow Inverter" ["Inverter"]
Group overview "Overview" (sungrowInverter)
Group batteryInformation "Battery information" (sungrowInverter)
Group gridInformation "Grid information" (sungrowInverter)
Group loadInformation "Load information" (sungrowInverter)

// Overview
Number:Power total_active_power "Total Active Power" (overview) ["Measurement", "Power"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-overview#sg-total-active-power"}
Number:Power total_dc_power "Total DC Power" (overview) ["Measurement", "Power"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-overview#sg-total-dc-power"}
Number:Energy daily_pv_generation "Daily PV Generation" (overview) ["Measurement", "Energy"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-overview#sg-daily-pv-generation"}
Number:Energy total_pv_generation "Total PV Generation" (overview) ["Measurement", "Energy"]  {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-overview#sg-total-pv-generation"}

// Battery information
Number:Power battery_power "Battery Power" (batteryInformation) ["Measurement", "Power"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-battery-information#sg-battery-power"}
Number:Dimensionless battery_level "Battery Level" (batteryInformation) ["Measurement", "Energy"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-battery-information#sg-battery-level"}
Number:Energy daily_charge_energy "Daily Battery Charge Energy" (batteryInformation) ["Measurement", "Energy"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-battery-information#sg-daily-charge-energy"}
Number:Energy daily_discharge_energy "Daily Battery Discharge Energy" (batteryInformation) ["Measurement", "Energy"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-battery-information#sg-daily-battery-discharge-energy"}

// Grid information
Number:Power export_power "Export Power" (gridInformation) ["Measurement", "Power"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-grid-information#sg-export-power"}
Number:Energy daily_export_energy "Daily Export Energy" (gridInformation) ["Measurement", "Energy"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-grid-information#sg-daily-export-energy"}
Number:Energy daily_import_energy "Daily Import Energy" (gridInformation) ["Measurement", "Energy"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-grid-information#sg-daily-import-energy"}

// Load information
Number:Power load_power "Load Power" (loadInformation) ["Measurement", "Power"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-load-information#sg-load-power"}
Number:Energy daily_direct_energy_consumption "Daily Direct Energy Consumption" (loadInformation) ["Measurement", "Energy"] {channel="modbus:sungrow-inverter:sungrowBridge:sungrowInverter:sg-load-information#sg-daily-direct-energy-consumption"}
```

_sungrow.sitemap_

```perl
sitemap sungrow label="Sungrow Binding"
{
    Frame {
        Text item=total_active_power
        Text item=total_dc_power
        Text item=daily_pv_generation
        Text item=total_pv_generation

        Text item=battery_power
        Text item=battery_level
        Text item=daily_charge_energy
        Text item=daily_discharge_energy

        Text item=export_power
        Text item=daily_export_energy
        Text item=daily_import_energy

        Text item=load_power
        Text item=daily_direct_energy_consumption
    }
}
```

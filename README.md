# Smart Home SOLID — Java

Refactoring de l'exercice 2 (domotique) avec **SRP** et **OCP**.

## Lancer

Exécuter `App.java` depuis VS Code ou :

```bash
javac -d bin src/controllers/*.java src/devices/*.java src/commands/*.java src/config/*.java src/App.java
java -cp bin App
```

## Structure

```
src/
├── App.java                       (point d'entrée + menu console)
├── config/
│   └── SmartHomeFactory.java      (composition / câblage)
├── commands/
│   ├── Command.java               (OCP — nouvelles actions sans modifier le menu)
│   ├── TurnOnLightCommand.java
│   ├── SetTemperatureCommand.java
│   ├── LockDoorsCommand.java
│   └── TurnOffAllCommand.java
├── controllers/
│   ├── LightController.java       (SRP)
│   ├── ThermostatController.java  (SRP)
│   └── SecurityController.java    (SRP)
└── devices/
    ├── IDevice.java               (OCP — nouveaux appareils sans modifier l'extinction)
    ├── LightDevice.java
    ├── ThermostatDevice.java
    └── SecurityAlarmDevice.java
```

## Extension sans modification

- **Nouvelle action** : créer une classe `Command`, l'ajouter à la liste dans `SmartHomeFactory`.
- **Nouvel appareil** : implémenter `IDevice`, l'ajouter dans `SmartHomeFactory.createDevices()`.

Les principes LSP, ISP et DIP seront abordés dans un module ultérieur.

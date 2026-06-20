# Smart Home SOLID — Java

Refactoring de l'exercice 2 (domotique) avec **SRP** et **OCP**.

## Lancer

Exécuter `App.java` depuis VS Code ou :

```bash
javac -d bin src/controllers/*.java src/devices/*.java src/SmartHomeApp.java src/App.java
java -cp bin App
```

## Structure

```
src/
├── SmartHomeApp.java
├── controllers/
│   ├── LightController.java       (SRP)
│   ├── ThermostatController.java  (SRP)
│   └── SecurityController.java    (SRP)
└── devices/
    ├── IDevice.java               (OCP)
    ├── LightDevice.java
    ├── ThermostatDevice.java
    └── SecurityAlarmDevice.java
```

Les principes LSP, ISP et DIP seront abordés dans un module ultérieur.

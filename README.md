# SpringBoot_Gallery
REST API Dokumentation

Projektübersicht

Dieses Projekt ist eine REST-API-Anwendung, die verschiedene Entitäten wie Benutzerkonten, Adressen, Fahrzeuge, Kunden und Währungskurse verwaltet. Die Anwendung basiert auf Spring Boot und folgt einer serviceorientierten Architektur. Sie bietet Authentifizierungs- und Autorisierungsmechanismen mit JWT, um eine sichere Nutzung der Endpunkte zu gewährleisten. Die API ermöglicht CRUD-Operationen für die verwalteten Entitäten und unterstützt eine einfache Integration mit externen Systemen.

Überblick

Dieses Projekt stellt eine Sammlung von REST-APIs bereit, die verschiedene Entitäten verwalten. Die Implementierung basiert auf dem Spring Boot Framework und verwendet eine serviceorientierte Architektur.

Technologien

Spring Boot wird als Framework für die Entwicklung von Java-basierten Webanwendungen genutzt. Spring REST ermöglicht die Erstellung von RESTful-Webservices, während Spring Validation zur Validierung von Eingabedaten dient. Spring Dependency Injection (DI) wird verwendet, um Abhängigkeiten zu verwalten. JWT (JSON Web Token) sorgt für die Authentifizierung und Autorisierung der Benutzer.

Verzeichnisstruktur

Das Projekt folgt einer klaren Struktur, in der verschiedene Komponenten wie Controller, Services, Repositories, DTOs, Modelle, Exceptions und Sicherheitselemente unterteilt sind. Die wichtigsten Controller sind für die Verwaltung von Accounts, Adressen, Authentifizierung, Fahrzeugen, Währungskursen, Kunden, Galleristen und Fahrzeugverkäufen zuständig.

REST-API Endpunkte

Account-Management

Der Endpunkt /rest/api/account/save ermöglicht das Erstellen oder Aktualisieren eines Kontos. Die Methode ist POST.

Adressverwaltung

Unter /rest/api/address/save können Adressen gespeichert werden. Die Methode ist POST.

Authentifizierung

Der Endpunkt /rest/api/authenticate dient zur Authentifizierung eines Benutzers und gibt ein JWT-Token zurück. Die Methode ist POST.

Fahrzeugverwaltung

Der Endpunkt /rest/api/car/save speichert Fahrzeuginformationen. Die Methode ist POST.

Währungskurse abrufen

Unter /rest/api/currency-rates können Währungskurse für einen bestimmten Zeitraum abgerufen werden. Die Methode ist GET.

Kundenverwaltung

Der Endpunkt /rest/api/customer/save erstellt neue Kundeneinträge. Die Methode ist POST.

Gallerist-Fahrzeugverwaltung

Unter /rest/api/gallerist-car/save können Informationen zu Gallerist-Fahrzeugen gespeichert werden. Die Methode ist POST.

Gallerist-Management

Der Endpunkt /rest/api/gallerist/save ermöglicht das Erstellen oder Aktualisieren von Galleristendaten. Die Methode ist POST.

Fahrzeugverkauf

Unter /rest/api/saled-car/save kann ein Auto gekauft und der Kauf gespeichert werden. Die Methode ist POST.

Sicherheit

Alle API-Endpunkte, die nicht der Authentifizierung dienen, sind durch JWT-Token gesichert. Authentifizierte Benutzer erhalten ein Token, das bei jeder Anfrage als Authorization: Bearer <TOKEN> Header gesendet werden muss. Benutzerrollen können mit Spring Security konfiguriert werden.

Voraussetzungen

Die Anwendung benötigt Java 17 oder höher, Maven sowie eine relationale Datenbank wie PostgreSQL.

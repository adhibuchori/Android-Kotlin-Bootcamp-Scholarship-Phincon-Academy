I'll revise the document to use cleaner, standard markdown syntax:

# Narumi Travel App Documentation

## Overview

![Narumi App Preview!](/Narumi-AppPreview.png "Narumi App Preview")

鳴海 (Narumi) is a travel app developed to help users search destinations, plan itineraries, make reservations, get recommendations, and validate bookings. This travel application developed for Phincon Academy Assignment Submission 3, designed to simplify trip planning and booking.

## Features

| Feature | Description | Benefit |
|---------|-------------|---------|
| Popular Trips | Curated travel destinations | Easy trip discovery |
| One-Click Checkout | Instant trip booking system | Simplified purchasing |
| Order History | Comprehensive booking tracking | Never lose travel records |

## Technical Architecture

### Tech Stack

| Category | Technology | Purpose | Key Advantages |
|----------|------------|---------|----------------|
| Database | Room Database | Local data storage | Efficient, reliable data management |
| Architecture | MVVM | Structural design pattern | Clear separation of concerns |
| Dependency Injection | Koin | Dependency injection | Modular, flexible configuration |
| UI Design | XML | User interface development | Native Android compatibility |

### Firebase Integration

| Service | Functionality | Key Benefits |
|---------|---------------|--------------|
| Firebase Analytics | User behavior tracking | Gain insights into app usage |
| Firebase Authentication | User login & registration | Secure, serverless authentication |
| Firebase Crashlytics | Error reporting | Proactive app stability monitoring |

## Project Structure

| Package | Responsibility | Main Components |
|---------|----------------|-----------------|
| Base | Core implementations | Abstract classes |
| Data | Data operations | Repository classes |
| DI | Dependency configuration | Koin providers |
| Domain | Layer interface contracts | Bridging data and UI |
| UI | User interface | Screens, layouts |
| Utils | Utility functions | Helper classes |

## Testing Coverage

### Login ViewModel Tests

| Scenario | Input | Expected Outcome | Validation |
|----------|-------|------------------|------------|
| Valid Login | Correct credentials | Successful authentication | Verify success message |
| Empty Credentials | Blank fields | Error handling | Check error message |

### Register ViewModel Tests

| Scenario | Input | Expected Outcome | Validation |
|----------|-------|------------------|------------|
| Valid Registration | Complete information | Registration success | Verify success message |
| Incomplete Registration | Partial/empty fields | Error handling | Validate error message |

### Checkout ViewModel Tests

| Scenario | Input | Expected Outcome | Validation |
|----------|-------|------------------|------------|
| Trip Retrieval | Valid Trip ID | Retrieve trip data | Verify data matching |
| Invalid Trip | Non-existent ID | Null handling | Graceful error management |

## Installation

| Version | Status | Download |
|---------|--------|----------|
| 1.0.0 | Stable Release | [Download Link](https://drive.google.com/file/d/1uvBxJ57AgWbHsNfxF2lkFt5O4fo6LPgt/view?usp=sharing) |

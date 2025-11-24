# Tourist Agency Management System

A full-stack web application for managing tourist agency operations with comprehensive CRUD functionality. Built with Angular frontend and Spring Boot backend, featuring tabular data management for travel packages, destinations, bookings, and clients.

## Project Overview

A professional administrative dashboard for tourist agency management, providing streamlined operations for managing travel arrangements, customer information, and agency resources. The system offers a clean tabular interface with full CRUD capabilities for all entities without authentication overhead, optimized for internal administrative use.

**Key Achievement:** Built complete full-stack CRUD application with RESTful API, reactive frontend, and PostgreSQL persistence layer.

## Technologies Used

### Frontend
- **Angular** - Modern TypeScript-based SPA framework
- **TypeScript** - Type-safe development
- **RxJS** - Reactive programming for HTTP operations
- **Angular Material / Bootstrap** - UI components and styling
- **HTML5/CSS3** - Markup and styling

### Backend
- **Spring Boot** - Java-based REST API framework
- **Spring Data JPA** - Database abstraction layer
- **Hibernate** - ORM for database operations
- **PostgreSQL** - Relational database
- **Maven** - Dependency management and build tool

### Development Tools
- **Git** - Version control
- **Postman** - API testing
- **pgAdmin** - PostgreSQL database management

## System Architecture

### Three-Tier Architecture

**Presentation Layer (Angular):**
- Tabular data display with sorting and filtering
- CRUD forms with validation
- Reactive state management
- RESTful API consumption

**Business Logic Layer (Spring Boot):**
- RESTful API endpoints
- Business logic processing
- Data validation
- Exception handling

**Data Layer (PostgreSQL):**
- Persistent data storage
- Relational data integrity
- ACID transactions

## Core Features

### Entity Management

#### 1. Travel Packages (Aranžmani)
- Package name and description
- Destination information
- Pricing and duration
- Available dates
- Capacity and current bookings

**Operations:**
- Create new travel packages
- View all packages in table
- Update package details
- Delete packages
- Search and filter

#### 2. Destinations (Destinacije)
- Destination name and country
- Description and attractions
- Climate and best visit times
- Popular activities

**Operations:**
- Add new destinations
- List all destinations
- Edit destination information
- Remove destinations

#### 3. Bookings (Rezervacije)
- Client information
- Selected package
- Travel dates
- Number of travelers
- Total price
- Booking status

**Operations:**
- Create bookings
- View booking history
- Modify booking details
- Cancel bookings

#### 4. Clients (Klijenti)
- Personal information (name, contact)
- Email and phone
- Address details
- Booking history reference

**Operations:**
- Register new clients
- View client database
- Update client information
- Delete client records

### Administrative Features

- **Tabular Interface:** All entities displayed in organized tables
- **Inline Editing:** Quick edit capabilities directly from tables
- **Bulk Operations:** Select multiple records for batch operations
- **Data Validation:** Form validation on frontend and backend
- **Error Handling:** User-friendly error messages
- **Responsive Design:** Works on desktop and tablet devices

### Key Tables

**clients**
- id (PK)
- first_name
- last_name
- email (unique)
- phone
- address
- created_at

**travel_packages**
- id (PK)
- package_name
- description
- destination_id (FK)
- price
- duration_days
- start_date
- end_date
- capacity
- available_spots

**bookings**
- id (PK)
- client_id (FK)
- package_id (FK)
- booking_date
- travel_date
- number_of_travelers
- total_price
- status (PENDING, CONFIRMED, CANCELLED)

**destinations**
- id (PK)
- name
- country
- description
- best_season
- activities

## RESTful API Endpoints

### Travel Packages
```
GET    /api/packages           - Get all packages
GET    /api/packages/{id}      - Get package by ID
POST   /api/packages           - Create new package
PUT    /api/packages/{id}      - Update package
DELETE /api/packages/{id}      - Delete package
GET    /api/packages/search    - Search packages
```

### Destinations
```
GET    /api/destinations       - Get all destinations
GET    /api/destinations/{id}  - Get destination by ID
POST   /api/destinations       - Create destination
PUT    /api/destinations/{id}  - Update destination
DELETE /api/destinations/{id}  - Delete destination
```

### Bookings
```
GET    /api/bookings           - Get all bookings
GET    /api/bookings/{id}      - Get booking by ID
POST   /api/bookings           - Create booking
PUT    /api/bookings/{id}      - Update booking
DELETE /api/bookings/{id}      - Delete booking
GET    /api/bookings/client/{clientId} - Get bookings by client
```

### Clients
```
GET    /api/clients            - Get all clients
GET    /api/clients/{id}       - Get client by ID
POST   /api/clients            - Create client
PUT    /api/clients/{id}       - Update client
DELETE /api/clients/{id}       - Delete client
GET    /api/clients/search     - Search clients
```

## Getting Started

### Prerequisites

**Frontend:**
- Node.js 14+
- npm 6+
- Angular CLI 12+

**Backend:**
- Java JDK 11+
- Maven 3.6+
- PostgreSQL 12+

### Database Setup

1. **Install PostgreSQL**

2. **Configure application.properties**

### Backend Setup

### Frontend Setup

### Testing the API

## Usage Examples

### Creating a Travel Package

**Frontend (Angular Service):**

**Backend (Spring Controller):**

### Retrieving All Bookings

**Frontend (Component):**

**Backend (Service):**

## Key Features Implementation

### 1. CRUD Operations

**Create:**
- Form validation on frontend
- DTO conversion on backend
- Database persistence
- Success/error feedback

**Read:**
- Paginated table display
- Sorting and filtering
- Detail view modal
- Related entity loading

**Update:**
- Pre-filled forms with current data
- Optimistic UI updates
- Validation on both layers
- Conflict resolution

**Delete:**
- Confirmation dialog
- Cascade handling for related entities
- Soft delete option (status update)
- Success notification

### 2. Reactive Programming

**Angular RxJS:**

### 3. Error Handling

**Backend Exception Handler:**

### 4. Data Validation

**Frontend Validation:**

**Backend Validation:**

## Key Learning Points

- **Full-Stack Development:** Integrated Angular and Spring Boot
- **RESTful API Design:** Created scalable API endpoints
- **ORM with JPA:** Mapped Java objects to database tables
- **Reactive Programming:** Used RxJS for asynchronous operations
- **Database Design:** Created normalized relational schema
- **CRUD Operations:** Implemented complete create-read-update-delete lifecycle
- **Error Handling:** Centralized exception handling on both layers
- **Form Validation:** Client and server-side validation
- **HTTP Communication:** RESTful communication between frontend and backend

## Design Decisions

### No Authentication
- **Rationale:** Internal administrative tool for single admin user
- **Use Case:** Trusted environment, rapid CRUD operations
- **Future:** Can easily add Spring Security + JWT if needed

### Tabular Interface
- **Rationale:** Efficient data viewing and management
- **Benefits:** Quick scanning, sorting, filtering
- **UX:** Familiar spreadsheet-like experience

### PostgreSQL Database
- **Rationale:** Robust relational database for complex relationships
- **Benefits:** ACID compliance, data integrity, powerful queries
- **Features:** Foreign keys, constraints, indexes

## Future Enhancements

- **Authentication:** Add Spring Security with JWT tokens
- **Role Management:** Admin, Agent, Viewer roles
- **Advanced Search:** Full-text search across entities
- **Reporting:** Generate PDF/Excel reports
- **Email Notifications:** Booking confirmations via email
- **Payment Integration:** Stripe/PayPal for online payments
- **Image Upload:** Package and destination photos
- **Multi-language:** i18n support for different languages
- **Audit Trail:** Track all changes with timestamps and user
- **Dashboard:** Analytics and statistics visualization

## Testing

### Manual Testing Checklist

- Create new travel package
- View all packages in table
- Edit package details
- Delete package
- Create booking for client
- View booking history
- Update booking status
- Search for clients
- Test foreign key constraints
- Verify data validation

### API Testing with Postman

Import collection with pre-configured requests for all endpoints.

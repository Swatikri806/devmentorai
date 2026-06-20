# DevMentorAI

A full-stack Java + React project built for AI-powered career coaching and code intelligence.

## Structure
- `devmentorai-backend`: Spring Boot backend with JWT auth, AI endpoints, and H2 database.
- `devmentorai-frontend`: React + Vite frontend for login, dashboard, and AI tools.

## Backend Setup
1. Open a terminal in `devmentorai-backend`.
2. Set your OpenAI API key:
   ```bash
   set OPENAI_API_KEY=your_api_key_here
   ```
3. Run:
   ```bash
   mvn spring-boot:run
   ```
4. Backend starts on `http://localhost:8080`

## Frontend Setup
1. Open a terminal in `devmentorai-frontend`.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Run:
   ```bash
   npm run dev
   ```
4. Frontend starts on `http://localhost:3000`

## Key Features
- JWT registration and login
- AI resume review
- AI code review
- AI interview question generation
- Profile dashboard with secure endpoints

## Notes
- The backend uses H2 in-memory database for development.
- Set the `OPENAI_API_KEY` environment variable before starting the backend.
- You can replace H2 with PostgreSQL by updating `application.yml` and database credentials.

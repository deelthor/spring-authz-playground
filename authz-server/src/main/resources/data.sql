INSERT INTO user (username, password, enabled) VALUES
  ('admin', '$2a$10$Fg.pwGKNEk8TtRq3C86DEeIo6CnUI05umcVQuvRh2DdwEKJUPtsJK', 1);

INSERT INTO role (username, role) VALUES
  ('admin', 'admin'),
  ('admin', 'user');
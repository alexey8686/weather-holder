db = db.getSiblingDB('weatherDb');
db.createUser({
  'user': 'user',
  'pwd': 'user',
  'roles': [
    {
      'role': 'read',
      'db': 'weatherDb'
    }
  ]
});
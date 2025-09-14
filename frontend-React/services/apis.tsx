// apiGetDevices.ts
const BASE_URL = "https://a5aaf56684c4.ngrok-free.app"; // sostituisci con il tuo link ngrok


// ✅ GET /api/devices
export const getDevices = async () => {
  try {
    const res = await fetch(`${BASE_URL}/api/devices`, {
      headers: {
        "ngrok-skip-browser-warning": "true"  // 👈 trucco ngrokw
      }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    const text = await res.text();
    const data = JSON.parse(text);
    return data;
  } catch (err) {

    console.error("Errore getDevices:", err);
    throw err;
  }
};

// ✅ GET /api/devices/count
export const getDeviceCount = async () => {
  try {
    const res = await fetch(`${BASE_URL}/api/devices/count`, {
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    const text = await res.text();
    return JSON.parse(text);
  } catch (err) {
    console.error("Errore getDeviceCount:", err);
    throw err;
  }
};

// ✅ GET /api/devices/status/{deviceId}
export const getStatusDevice = async (deviceId: string) => {
  try {
    const res = await fetch(`${BASE_URL}/api/devices/status/${deviceId}`, {
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.text();
  } catch (err) {
    console.error("Errore getStatusDevice:", err);
    throw err;
  }
};

// GET /api/alarm/status
export const getAlarmStatus = async () => {
  try {
    const res = await fetch(`${BASE_URL}/api/alarm/status`, {
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.text();
  }
  catch (err) {
    console.error("Errore getAlarmStatus:", err);
    throw err;
  }
};


// ✅ POST generico senza body
const postNoBody = async (endpoint: string) => {
  try {
    const res = await fetch(`${BASE_URL}${endpoint}`, {
      method: "POST",
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.text();
  } catch (err) {
    console.error(`Errore POST ${endpoint}:`, err);
    throw err;
  }
};

// ✅ POST con parametri query string
const postWithParams = async (endpoint: string, params: Record<string, any>) => {
  const query = new URLSearchParams(params).toString();
  try {
    const res = await fetch(`${BASE_URL}${endpoint}?${query}`, {
      method: "POST",
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.text();
  } catch (err) {
    console.error(`Errore POST ${endpoint}:`, err);
    throw err;
  }
};

// ✅ POST /api/alarm/activate // take enable as param boolean
export const turnOnAlarm = async (enable: boolean) => {
  try {
    const res = await fetch(`${BASE_URL}/api/alarm/activate?enable=${enable}`, {
      method: "POST",
      headers: { "ngrok-skip-browser-warning": "true" },
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.text();
  } catch (err) {
    console.error("Errore turnOnAlarm:", err);
    throw err;
  }
};

export const registerToken = async (token: string) => {
  try {
    const res = await fetch(`${BASE_URL}/api/alarm/register-token`, {
      method: "POST",
      headers: { "Content-Type": "application/json", "ngrok-skip-browser-warning": "true" },
      body: JSON.stringify({ token }),
    });
    console.log(res);
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.text();
  } catch (err) {
    console.error("Errore registerToken:", err);
    throw err;
  }
};

// 🔹 Azioni device
export const turnOnDevice = (deviceId: string) =>
  postNoBody(`/api/devices/turnOnDevice/${deviceId}`);

export const turnOffDevice = (deviceId: string) =>
  postNoBody(`/api/devices/turnOffDevice/${deviceId}`);

export const startDevice = (deviceId: string) =>
  postNoBody(`/api/devices/startDevice/${deviceId}`);

export const stopDevice = (deviceId: string) =>
  postNoBody(`/api/devices/stopDevice/${deviceId}`);

export const setUpBlind = (deviceId: string) =>
  postNoBody(`/api/devices/setupBlind/${deviceId}`);

// missing
export const setDownBlind = (deviceId: string) =>
  postNoBody(`/api/devices/setdownBlind/${deviceId}`);


export const setProgramDevice = (deviceId: string, program: number) =>
  postWithParams(`/api/devices/setProgram/${deviceId}`, { program });

export const setTemperatureOven = (deviceId: string, temperature: number) =>
  postWithParams(`/api/devices/setTemperatureOven/${deviceId}`, { temperature });


// 🔹 Rooms
export const createRoom = (roomId: string) =>
  postNoBody(`/api/devices/rooms/${roomId}`);

export const addDeviceToRoom = (roomId: string, deviceId: string) =>
  postNoBody(`/api/devices/rooms/${roomId}/devices/${deviceId}`);

export const removeDeviceFromRoom = (roomId: string, deviceId: string) =>
  fetch(`${BASE_URL}/api/devices/rooms/${roomId}/devices/${deviceId}`, {
    method: "DELETE",
    headers: { "ngrok-skip-browser-warning": "true" },
  }).then(res => res.ok ? res.text() : Promise.reject(`Errore ${res.status}`));

export const listRoomDevices = (roomId: string) =>
  fetch(`${BASE_URL}/api/devices/rooms/${roomId}/devices`, {
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));

export const turnOnRoom = (roomId: string) =>
  fetch(`${BASE_URL}/api/devices/rooms/${roomId}/turnOn`, {
    method: "POST",
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));

export const turnOffRoom = (roomId: string) =>
  fetch(`${BASE_URL}/api/devices/rooms/${roomId}/turnOff`, {
    method: "POST",
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));

export const startRoom = (roomId: string) =>
  postNoBody(`/api/devices/rooms/${roomId}/start`);

export const stopRoom = (roomId: string) =>
  postNoBody(`/api/devices/rooms/${roomId}/stop`);

export const setProgramRoom = (roomId: string, program: number) =>
  postWithParams(`/api/devices/rooms/${roomId}/setProgram`, { program });

export const setTemperatureRoom = (roomId: string, temperature: number) =>
  postWithParams(`/api/devices/rooms/${roomId}/setTemperature`, { temperature });

// 🔹 Groups
export const createGroup = (groupId: string) =>
  postNoBody(`/api/devices/groups/${groupId}`);

export const addDeviceToGroup = (groupId: string, deviceId: string) =>
  postNoBody(`/api/devices/groups/${groupId}/devices/${deviceId}`);

export const removeDeviceFromGroup = (groupId: string, deviceId: string) =>
  fetch(`${BASE_URL}/api/devices/groups/${groupId}/devices/${deviceId}`, {
    method: "DELETE",
    headers: { "ngrok-skip-browser-warning": "true" },
  }).then(res => res.ok ? res.text() : Promise.reject(`Errore ${res.status}`));

export const listGroupDevices = (groupId: string) =>
  fetch(`${BASE_URL}/api/devices/groups/${groupId}/devices`, {
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));

export const turnOnGroup = (groupId: string) =>
  fetch(`${BASE_URL}/api/devices/groups/${groupId}/turnOn`, {
    method: "POST",
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));

export const turnOffGroup = (groupId: string) =>
  fetch(`${BASE_URL}/api/devices/groups/${groupId}/turnOff`, {
    method: "POST",
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));

export const startGroup = (groupId: string) =>
  postNoBody(`/api/devices/groups/${groupId}/start`);

export const stopGroup = (groupId: string) =>
  postNoBody(`/api/devices/groups/${groupId}/stop`);

export const setProgramGroup = (groupId: string, program: number) =>
  postWithParams(`/api/devices/groups/${groupId}/setProgram`, { program });

export const setTemperatureGroup = (groupId: string, temperature: number) =>
  postWithParams(`/api/devices/groups/${groupId}/setTemperature`, { temperature });

// 🔹 Misc
export const listRooms = () =>
  fetch(`${BASE_URL}/api/devices/rooms`, { headers: { "ngrok-skip-browser-warning": "true" } })
    .then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));

export const listGroups = () =>
  fetch(`${BASE_URL}/api/devices/groups`, { headers: { "ngrok-skip-browser-warning": "true" } })
    .then(res => res.ok ? res.json() : Promise.reject(`Errore ${res.status}`));



/////////////////////// NEW ///////////////////////


// GET /api/logs/recent?limit=50
export const getRecentLogs = async (limit: number = 50) => {
  try {
    const res = await fetch(`${BASE_URL}/api/devices/logs/recent?limit=${limit}`, {
      method: "GET",
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.json();
  } catch (err) {
    console.error("Errore getRecentLogs:", err);
    throw err;
  }
};

// GET /api/logs/byDevice/{deviceId}?limit=50
export const getLogsByDevice = async (deviceId: string, limit: number = 50) => {
  try {
    const res = await fetch(`${BASE_URL}/api/devices/logs/byDevice/${deviceId}?limit=${limit}`, {
      method: "GET",
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.json();
  } catch (err) {
    console.error("Errore getLogsByDevice:", err);
    throw err;
  }
};


// PATCH /api/rooms/{oldId}/rename?newId=xxx
export const renameRoom = (oldId: string, newId: string) =>
  postWithParams(`/api/devices/rooms/${oldId}/rename`, { newId });

// DELETE /api/rooms/{roomId}
export const deleteRoom = (roomId: string) =>
  fetch(`${BASE_URL}/api/devices/rooms/${roomId}`, {
    method: "DELETE",
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => {
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return res.text();
  });



// DELETE /api/groups/{groupId}
export const deleteGroup = (groupId: string) =>
  fetch(`${BASE_URL}/api/devices/groups/${groupId}`, {
    method: "DELETE",
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => {
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return res.text();
  });


  // PATCH /api/devices/{oldId}/rename?newId=xxx
export const renameDevice = (oldId: string, newId: string) =>
  postWithParams(`/api/devices/devices/${oldId}/rename`, { newId });

// DELETE /api/devices/{deviceId}/displayName
export const clearDisplayName = (deviceId: string) =>
  fetch(`${BASE_URL}/api/devices/devices/${deviceId}/displayName`, {
    method: "DELETE",
    headers: { "ngrok-skip-browser-warning": "true" }
  }).then(res => {
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return res.text();
  });

// GET /api/devices/displayNames
export const listDisplayNames = async () => {
  try {
    const res = await fetch(`${BASE_URL}/api/devices/devices/displayNames`, {
      method: "GET",
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.json() as Record<string, string>; // Map deviceId → displayName
  } catch (err) {
    console.error("Errore listDisplayNames:", err);
    throw err;
  }
};



const patchWithParams = async (endpoint: string, params: Record<string, any>) => {
  const query = new URLSearchParams(params).toString();
  try {
    const res = await fetch(`${BASE_URL}${endpoint}?${query}`, {
      method: "PATCH",
      headers: { "ngrok-skip-browser-warning": "true" }
    });
    if (!res.ok) throw new Error(`Errore ${res.status}`);
    return await res.text();
  } catch (err) {
    console.error(`Errore PATCH ${endpoint}:`, err);
    throw err;
  }
};

// await patchWithParams(`/api/devices/groups/${oldId}/rename`, { newId });


export const renameGroup = (oldId: string, newId: string) =>
  patchWithParams(`/api/devices/groups/${oldId}/rename`, { newId });


export const setDisplayName = (deviceId: string, name: string) =>
  patchWithParams(`/api/devices/devices/${deviceId}/displayName`, { name });

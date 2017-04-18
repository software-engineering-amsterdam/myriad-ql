# -*- coding: utf-8 -*-
"""
This module defines a simple event manager that works on a basis of publishers
and subscribers. This is used in combination with the class `ui.context` to
update the values of the variables when they change.
"""


class EventManager(object):
    """
    Simple event manager based on the bulletin board pattern. It defines a
    unique handler and a unique event for simplicity.
    """
    def __init__(self, updater):
        """Initialises the event manager and stores the update function."""
        self._subscriptions = {}
        self.updater = updater

    def get_publishers(self):
        """Retrieves all the declared publishers."""
        return self._subscriptions.keys()

    def subscribe(self, subscriber, publisher):
        """Subscribers the `subscriber` to the `publisher` events"""
        if publisher in self._subscriptions:
            self._subscriptions[publisher].append(subscriber)
        else:
            self._subscriptions[publisher] = [subscriber]

    def publish(self, publisher):
        """Updates all the subscribers of the `publisher`"""
        if publisher in self._subscriptions:
            for subscriber in self._subscriptions[publisher]:
                self.updater(subscriber)
